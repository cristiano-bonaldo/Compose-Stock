package cvb.com.br.composestockmarket.data.repository

import cvb.com.br.composestockmarket.domain.data_source.StockIntraDayDataSource
import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import cvb.com.br.composestockmarket.domain.repository.StockIntraDayRepository
import javax.inject.Inject

class StockIntraDayRepositoryImpl @Inject constructor(
    private val remoteDataSource: StockIntraDayDataSource
) : StockIntraDayRepository {
    override suspend fun getListStockIntraDay(symbol: String): List<StockIntraDay> {
        return remoteDataSource.getListStockIntraDay(symbol)
    }
}