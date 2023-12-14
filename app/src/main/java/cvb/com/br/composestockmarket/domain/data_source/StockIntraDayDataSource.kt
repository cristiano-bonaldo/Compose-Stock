package cvb.com.br.composestockmarket.domain.data_source

import cvb.com.br.composestockmarket.domain.model.StockIntraDay

interface StockIntraDayDataSource {
    suspend fun getListStockIntraDay(symbol: String): List<StockIntraDay>
}