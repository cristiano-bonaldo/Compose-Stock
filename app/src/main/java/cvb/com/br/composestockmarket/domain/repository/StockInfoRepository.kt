package cvb.com.br.composestockmarket.domain.repository

import cvb.com.br.composestockmarket.domain.model.StockInfo

interface StockInfoRepository {

    suspend fun getStockInfo(symbol: String): StockInfo

}