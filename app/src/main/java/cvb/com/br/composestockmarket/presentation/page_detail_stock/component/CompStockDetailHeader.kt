package cvb.com.br.composestockmarket.presentation.page_detail_stock.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cvb.com.br.composestockmarket.R
import cvb.com.br.composestockmarket.domain.model.StockDetail

@Composable
fun CompStockDetailHeader(stockDetail: StockDetail) {
    Text(
        text = "[ ${stockDetail.stockInfo.symbol} ]",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    )

    Text(
        text = stockDetail.stockInfo.name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 22.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(6.dp))

    Divider(
        thickness = 1.dp,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 5.dp)
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = stringResource(id = R.string.country),
        fontSize = 14.sp,
        color = Color.White,
        fontWeight = FontWeight.Thin
    )

    Text(
        text = stockDetail.stockInfo.country,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = stringResource(id = R.string.industry),
        fontSize = 14.sp,
        color = Color.White,
        fontWeight = FontWeight.Thin
    )

    Text(
        text = stockDetail.stockInfo.industry,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = stringResource(id = R.string.description),
        fontSize = 14.sp,
        color = Color.White,
        fontWeight = FontWeight.Thin
    )

    Text(
        text = stockDetail.stockInfo.description,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    )

    Spacer(modifier = Modifier.height(6.dp))
}
