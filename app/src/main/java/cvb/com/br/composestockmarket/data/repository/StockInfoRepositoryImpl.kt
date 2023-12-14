package cvb.com.br.composestockmarket.data.repository

import cvb.com.br.composestockmarket.domain.data_source.StockInfoDataSource
import cvb.com.br.composestockmarket.domain.model.StockInfo
import cvb.com.br.composestockmarket.domain.repository.StockInfoRepository
import javax.inject.Inject

class StockInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: StockInfoDataSource
) : StockInfoRepository {
    override suspend fun getStockInfo(symbol: String): StockInfo {
        return remoteDataSource.getStockInfo(symbol)
    }
}