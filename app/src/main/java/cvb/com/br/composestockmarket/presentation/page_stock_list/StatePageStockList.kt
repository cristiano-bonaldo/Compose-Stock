package cvb.com.br.composestockmarket.presentation.page_stock_list

import cvb.com.br.composestockmarket.domain.model.Stock
import cvb.com.br.composestockmarket.presentation.ui.util.StateError

data class StatePageStockList(
    val isLoading: Boolean = false,
    val listStock: List<Stock> = emptyList(),
    val stateError: StateError? = null,
    val showRetryDialog: Boolean = false,
    val isRefreshing: Boolean = false,
    val textSearchQuery: String = "",
)