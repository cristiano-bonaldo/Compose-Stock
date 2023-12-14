package cvb.com.br.composestockmarket.data.mapper

import cvb.com.br.composestockmarket.data.api.dto.StockIntraDayDto
import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun StockIntraDayDto.toStockIntraDay(): StockIntraDay {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val dateTime = LocalDateTime.parse(this.timestamp, formatter)

    return StockIntraDay(dateTime = dateTime, value = this.close.toDouble())
}
