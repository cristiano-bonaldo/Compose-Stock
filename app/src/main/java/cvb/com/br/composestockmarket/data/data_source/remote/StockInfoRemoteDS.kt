package cvb.com.br.composestockmarket.data.data_source.remote

import cvb.com.br.composecripto.data.api.util.ApiHandleDataResult
import cvb.com.br.composestockmarket.data.api.service.ApiService
import cvb.com.br.composestockmarket.data.mapper.toStockInfo
import cvb.com.br.composestockmarket.domain.data_source.StockInfoDataSource
import cvb.com.br.composestockmarket.domain.model.StockInfo
import javax.inject.Inject

class StockInfoRemoteDS @Inject constructor(
    private val api: ApiService
) : StockInfoDataSource {
    override suspend fun getStockInfo(symbol: String): StockInfo {
        val stockInfoDto = ApiHandleDataResult.handleData { api.getStockInfo(symbol) }

        return stockInfoDto.toStockInfo()
    }
}