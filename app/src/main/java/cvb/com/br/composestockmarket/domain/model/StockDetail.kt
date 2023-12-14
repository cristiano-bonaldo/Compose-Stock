package cvb.com.br.composestockmarket.domain.model

data class StockDetail(
    val stockInfo: StockInfo,
    val listStockIntraDay: List<StockIntraDay>
)