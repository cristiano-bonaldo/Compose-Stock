package cvb.com.br.composestockmarket.presentation.page_detail_stock

sealed class EventPageDetailStock {

    data object RetryLoadData : EventPageDetailStock()

    data object DismissDialog : EventPageDetailStock()

}