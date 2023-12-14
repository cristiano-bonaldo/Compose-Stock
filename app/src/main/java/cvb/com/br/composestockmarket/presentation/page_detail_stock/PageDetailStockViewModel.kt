package cvb.com.br.composestockmarket.presentation.page_detail_stock

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cvb.com.br.composestockmarket.R
import cvb.com.br.composestockmarket.domain.model.StockDetail
import cvb.com.br.composestockmarket.domain.use_case.StockUseCase
import cvb.com.br.composestockmarket.domain.util.Resource
import cvb.com.br.composestockmarket.presentation.ui.util.StateError
import cvb.com.br.composestockmarket.presentation.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class PageDetailStockViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val stockUseCase: StockUseCase
) :
    ViewModel() {

    private val mState = mutableStateOf(StatePageDetailStock())
    val state: State<StatePageDetailStock>
        get() = mState

    private var stockSymbol: String? = null

    init {
        stockSymbol = savedStateHandle.get<String>(Constant.NAV_PARAM_STOCK_SYMBOL)
        loadDetailStock()
    }

    private fun loadDetailStock() {
        stockSymbol?.let { symbol ->

            viewModelScope.launch {
                mState.value = mState.value.copy(isLoading = true)

                val defStockInfo = async { stockUseCase.getStockInfo(symbol) }
                val defListStockIntraDay = async { stockUseCase.getListStockIntraDay(symbol) }

                val resultStockInfo = defStockInfo.await()
                val resultStockIntraDay = defListStockIntraDay.await()

                if ((resultStockInfo is Resource.Success) && (resultStockIntraDay is Resource.Success)) {
                    if (resultStockInfo.data == null || resultStockInfo.data.symbol.isEmpty() || resultStockIntraDay.data.isNullOrEmpty()
                    ) {
                        val stateError =
                            StateError(msg = null, idDefaultMsg = R.string.error_invalid_data)
                        mState.value =
                            mState.value.copy(isLoading = false, stateError = stateError, showRetryDialog = true)
                    } else {
                        val stockDetail =
                            StockDetail(resultStockInfo.data, resultStockIntraDay.data)
                        mState.value =
                            mState.value.copy(isLoading = false, stockDetail = stockDetail)
                    }
                } else {
                    val stateError: StateError =
                        if (resultStockInfo is Resource.Error) {
                            StateError(resultStockIntraDay.message)
                        } else if (resultStockIntraDay is Resource.Error) {
                            StateError(resultStockIntraDay.message)
                        } else {
                            StateError(null)
                        }

                    mState.value = mState.value.copy(isLoading = false, stateError = stateError, showRetryDialog = true)
                }
            }
        }
    }

    private fun dismissRetryDialog() {
        mState.value = mState.value.copy(stateError = null, showRetryDialog = false)
    }

    fun handleEvent(event: EventPageDetailStock) {
        when (event) {
            is EventPageDetailStock.RetryLoadData -> {
                dismissRetryDialog()
                loadDetailStock()
            }

            is EventPageDetailStock.DismissDialog -> {
                dismissRetryDialog()
            }
        }
    }
}