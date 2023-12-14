package cvb.com.br.composestockmarket.presentation.page_stock_list

sealed class EventPageStockList() {

    data object RetryLoadData : EventPageStockList()

    data object DismissDialog : EventPageStockList()

    data object RefreshData : EventPageStockList()

    class UpdateTextSearchQuery(val text: String) : EventPageStockList()

    data object ClearTextSearchQuery : EventPageStockList()
}