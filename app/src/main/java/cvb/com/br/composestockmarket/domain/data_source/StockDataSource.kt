package cvb.com.br.composestockmarket.domain.data_source

import cvb.com.br.composestockmarket.domain.model.Stock

interface StockDataSource {
    suspend fun insertListStock(listStock: List<Stock>)

    suspend fun deleteAllStock()

    suspend fun queryListStock(query: String): List<Stock>

    suspend fun getListStock(): List<Stock>
}