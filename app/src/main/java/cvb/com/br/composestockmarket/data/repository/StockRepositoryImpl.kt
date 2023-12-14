package cvb.com.br.composestockmarket.data.repository

import cvb.com.br.composestockmarket.di.StockLocalDataSource
import cvb.com.br.composestockmarket.di.StockRemoteDataSource
import cvb.com.br.composestockmarket.domain.data_source.StockDataSource
import cvb.com.br.composestockmarket.domain.model.Stock
import cvb.com.br.composestockmarket.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    @StockLocalDataSource private val localDataSource: StockDataSource,
    @StockRemoteDataSource private val remoteDataSource: StockDataSource
) : StockRepository {
    override suspend fun insertListStock(listStock: List<Stock>) {
        localDataSource.insertListStock(listStock)
    }

    override suspend fun deleteAllStock() {
        localDataSource.deleteAllStock()
    }

    override suspend fun queryListStock(query: String): List<Stock> {
        return localDataSource.queryListStock(query)
    }

    override suspend fun getListStock(): List<Stock> {
        return remoteDataSource.getListStock()
    }
}