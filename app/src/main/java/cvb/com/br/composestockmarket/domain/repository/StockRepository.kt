package cvb.com.br.composestockmarket.domain.repository

import cvb.com.br.composestockmarket.domain.model.Stock

interface StockRepository {

    suspend fun insertListStock(listStock: List<Stock>)

    suspend fun deleteAllStock()

    suspend fun queryListStock(query: String): List<Stock>

    suspend fun getListStock(): List<Stock>

}