package cvb.com.br.composestockmarket.domain.model

import java.time.LocalDateTime

data class StockIntraDay(
    val dateTime: LocalDateTime,
    val value: Double
)