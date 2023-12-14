package cvb.com.br.composestockmarket.presentation.page_detail_stock

import cvb.com.br.composestockmarket.domain.model.Stock
import cvb.com.br.composestockmarket.domain.model.StockDetail
import cvb.com.br.composestockmarket.presentation.ui.util.StateError

data class StatePageDetailStock(
    val isLoading: Boolean = false,
    val stockDetail: StockDetail? = null,
    val stateError: StateError? = null,
    val showRetryDialog: Boolean = false,
)