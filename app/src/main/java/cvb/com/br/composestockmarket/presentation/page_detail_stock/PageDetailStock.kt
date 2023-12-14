package cvb.com.br.composestockmarket.presentation.page_detail_stock

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cvb.com.br.composestockmarket.R
import cvb.com.br.composestockmarket.presentation.component.CompLoadingData
import cvb.com.br.composestockmarket.presentation.component.CompRetryDialog
import cvb.com.br.composestockmarket.presentation.component.CompTopBar
import cvb.com.br.composestockmarket.presentation.page_detail_stock.component.CompStockChart
import cvb.com.br.composestockmarket.presentation.page_detail_stock.component.CompStockDetailHeader

@Composable
fun PageDetailStock(
    viewModel: PageDetailStockViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            CompTopBar(R.string.detail, Icons.Rounded.ArrowBack) { navController.popBackStack() }
        },
    ) { innerPadding ->
        PageContent(viewModel, innerPadding)
    }
}

@Composable
fun PageContent(
    viewModel: PageDetailStockViewModel,
    paddingValues: PaddingValues
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        state.stockDetail?.let { stockDetail ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    CompStockDetailHeader(stockDetail)
                }

                Spacer(modifier = Modifier.height(2.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Market Summary:",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    CompStockChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(250.dp)
                            .align(Alignment.CenterHorizontally),
                        stockDetail.listStockIntraDay
                    )
                }
            }
        }

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
                        viewModel.handleEvent(EventPageDetailStock.RetryLoadData)
                    },
                    onCancel = {
                        viewModel.handleEvent(EventPageDetailStock.DismissDialog)
                    },
                    dialogTitle = stringResource(id = R.string.error),
                    dialogText = msgError,
                    icon = Icons.Rounded.Warning
                )
            }
        }
    }
}


