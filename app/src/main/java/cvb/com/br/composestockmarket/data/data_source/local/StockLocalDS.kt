package cvb.com.br.composestockmarket.data.data_source.local

import cvb.com.br.composestockmarket.data.db.dao.StockDao
import cvb.com.br.composestockmarket.data.mapper.toStock
import cvb.com.br.composestockmarket.data.mapper.toStockEntity
import cvb.com.br.composestockmarket.domain.data_source.StockDataSource
import cvb.com.br.composestockmarket.domain.model.Stock
import javax.inject.Inject

class StockLocalDS @Inject constructor(private val dao: StockDao) : StockDataSource {
    override suspend fun insertListStock(listStock: List<Stock>) {
        val listStockEntity = listStock.map { stock -> stock.toStockEntity() }
        dao.insertListStock(listStockEntity)
    }

    override suspend fun deleteAllStock() {
        dao.deleteAllStock()
    }

    override suspend fun queryListStock(query: String): List<Stock> {
        val listStockEntity = dao.queryListStockEntity(query)
        return listStockEntity.map { stockEntity -> stockEntity.toStock() }
    }

    override suspend fun getListStock(): List<Stock> {
        val listStockEntity = dao.getListStockEntity()
        return listStockEntity.map { stockEntity -> stockEntity.toStock() }
    }
}