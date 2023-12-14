package cvb.com.br.composestockmarket.domain.repository

import cvb.com.br.composestockmarket.domain.model.StockIntraDay

interface StockIntraDayRepository {

    suspend fun getListStockIntraDay(symbol: String): List<StockIntraDay>

}