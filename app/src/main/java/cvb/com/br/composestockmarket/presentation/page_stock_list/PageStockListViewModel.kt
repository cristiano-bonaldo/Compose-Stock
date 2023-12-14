package cvb.com.br.composestockmarket.presentation.page_stock_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cvb.com.br.composestockmarket.domain.use_case.StockUseCase
import cvb.com.br.composestockmarket.domain.util.Resource
import cvb.com.br.composestockmarket.presentation.ui.util.StateError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageStockListViewModel @Inject constructor(private val stockUseCase: StockUseCase) :
    ViewModel() {

    private val mState = mutableStateOf(StatePageStockList())
    val state: State<StatePageStockList>
        get() = mState

    private var jobSearch: Job? = null

    init {
        loadListStock()
    }

    private fun loadListStock() {
        stockUseCase.queryListStock.invoke(
            updateDataFromRemoteSource = mState.value.isRefreshing,
            query = mState.value.textSearchQuery
        ).onEach { resource ->
            when (resource) {
                is Resource.Loading ->
                    mState.value =
                        mState.value.copy(isLoading = true)

                is Resource.Error ->
                    mState.value =
                        mState.value.copy(isLoading = false, isRefreshing = false, stateError = StateError(resource.message), showRetryDialog = true)

                is Resource.Success ->
                    mState.value =
                        mState.value.copy(isLoading = false, isRefreshing = false, listStock = resource.data ?: emptyList())
            }

        }.launchIn(viewModelScope)
    }

    private fun dismissRetryDialog() {
        mState.value = mState.value.copy(stateError = null, showRetryDialog = false)
    }

    private fun updateTextSearchQuery(text: String) {
        mState.value = mState.value.copy(textSearchQuery = text)
    }

    private fun updateStatusForRefreshData(status: Boolean) {
        mState.value = mState.value.copy(isRefreshing = status)
    }

    fun handleEvent(event: EventPageStockList) {
        when (event) {
            is EventPageStockList.RetryLoadData -> {
                updateStatusForRefreshData(true)
                dismissRetryDialog()
                loadListStock()
            }

            is EventPageStockList.RefreshData -> {
                updateStatusForRefreshData(true)
                loadListStock()
            }

            is EventPageStockList.DismissDialog -> {
                dismissRetryDialog()
            }

            is EventPageStockList.UpdateTextSearchQuery -> {
                updateStatusForRefreshData(false)
                updateTextSearchQuery(event.text)
                jobSearch?.cancel()
                jobSearch = viewModelScope.launch {
                    delay(500)
                    loadListStock()
                }
            }

            is EventPageStockList.ClearTextSearchQuery -> {
                updateStatusForRefreshData(false)
                updateTextSearchQuery("")
                loadListStock()
            }
        }
    }
}