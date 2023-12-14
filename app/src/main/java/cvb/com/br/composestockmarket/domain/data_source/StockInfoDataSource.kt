package cvb.com.br.composestockmarket.domain.data_source

import cvb.com.br.composestockmarket.domain.model.StockInfo

interface StockInfoDataSource {
    suspend fun getStockInfo(symbol: String): StockInfo
}