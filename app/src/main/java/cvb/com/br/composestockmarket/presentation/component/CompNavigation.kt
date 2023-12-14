package cvb.com.br.composestockmarket.presentation.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cvb.com.br.composestockmarket.presentation.page_detail_stock.PageDetailStock
import cvb.com.br.composestockmarket.presentation.page_stock_list.PageStockList
import cvb.com.br.composestockmarket.presentation.util.Constant
import cvb.com.br.composestockmarket.presentation.util.NavRoute

@Composable
fun CompNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.RouteStockList.route) {

        composable(route = NavRoute.RouteStockList.route) {
            PageStockList(navController = navController)
        }

        composable(route = NavRoute.RouteStockDetail.route + "/{${Constant.NAV_PARAM_STOCK_SYMBOL}}") {
            PageDetailStock(navController = navController)
        }
    }
}