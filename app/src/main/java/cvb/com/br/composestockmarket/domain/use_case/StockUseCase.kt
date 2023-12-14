package cvb.com.br.composestockmarket.domain.use_case

import javax.inject.Inject

data class StockUseCase @Inject constructor(
    val queryListStock: QueryListStock,
    val getStockInfo: GetStockInfo,
    val getListStockIntraDay: GetListStockIntraDay
)