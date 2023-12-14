package cvb.com.br.composestockmarket.presentation.util

sealed class NavRoute(val route: String) {

    data object RouteStockList: NavRoute("page_stock_list")

    data object RouteStockDetail: NavRoute("page_stock_detail")

}
