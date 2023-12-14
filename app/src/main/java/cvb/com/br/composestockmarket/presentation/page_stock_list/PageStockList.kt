package cvb.com.br.composestockmarket.presentation.page_stock_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cvb.com.br.composestockmarket.R
import cvb.com.br.composestockmarket.presentation.component.CompLoadingData
import cvb.com.br.composestockmarket.presentation.component.CompRetryDialog
import cvb.com.br.composestockmarket.presentation.component.CompTopBar
import cvb.com.br.composestockmarket.presentation.page_stock_list.component.CompStockRow
import cvb.com.br.composestockmarket.presentation.util.NavRoute
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState

@Composable
fun PageStockList(
    viewModel: PageStockListViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            CompTopBar(R.string.app_name, Icons.Rounded.Home) { }
        },
    ) { innerPadding ->
        PageContent(viewModel, navController, innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageContent(
    viewModel: PageStockListViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {
    val state = viewModel.state.value

    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = { viewModel.handleEvent(EventPageStockList.RefreshData) }
        )

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            CompSearchArea(viewModel)

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                Log.i("Bonaldo", "Imprimindo dados")
                items(state.listStock) { stock ->
                    CompStockRow(stock = stock, onClick = {
                        navController.navigate(route = NavRoute.RouteStockDetail.route + "/" + stock.symbol)
                    })
                }
            }
        }

        PullRefreshIndicator(
            refreshing = state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(alignment = Alignment.TopCenter)
        )

        if (state.isLoading) {
            Log.i("Bonaldo", "Carregando dados")
            CompLoadingData()
        }

        state.stateError?.let { stateError ->
            if (state.showRetryDialog) {
                Log.i("Bonaldo", "Tratamento de Erro")

                val msgError =
                    stateError.msg ?: run { stringResource(id = stateError.idDefaultMsg) }

                CompRetryDialog(
                    onDismissRequest = { },
                    onRetry = {
                        viewModel.handleEvent(EventPageStockList.RetryLoadData)
                    },
                    onCancel = {
                        viewModel.handleEvent(EventPageStockList.DismissDialog)
                    },
                    dialogTitle = stringResource(id = R.string.error),
                    dialogText = msgError,
                    icon = Icons.Rounded.Warning
                )
            }
        }
    }
}

@Composable
fun CompSearchArea(viewModel: PageStockListViewModel, modifier: Modifier = Modifier) {
    val state = viewModel.state.value

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = state.textSearchQuery,
        onValueChange = {
            viewModel.handleEvent(
                EventPageStockList.UpdateTextSearchQuery(
                    it
                )
            )
        },
        label = { Text(stringResource(id = R.string.filter)) },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            if (state.textSearchQuery.isNotEmpty()) {
                IconButton(
                    onClick = { viewModel.handleEvent(EventPageStockList.ClearTextSearchQuery) },
                    content = {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
            }
        }
    )

    Text(
        text = stringResource(id = R.string.total, state.listStock.size.toString()),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End
    )

    Spacer(modifier = Modifier.height(10.dp))

    Divider(
        modifier = Modifier.padding(horizontal = 25.dp),
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.primary
    )

    Spacer(modifier = Modifier.height(5.dp))
}