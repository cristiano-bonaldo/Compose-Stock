package cvb.com.br.composestockmarket.data.data_source.remote

import cvb.com.br.composestockmarket.data.api.service.ApiService
import cvb.com.br.composestockmarket.data.csv.CSVParser
import cvb.com.br.composestockmarket.domain.data_source.StockIntraDayDataSource
import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import javax.inject.Inject

class StockIntraDayRemoteDS @Inject constructor(
    private val api: ApiService,
    private val csvParser: CSVParser<StockIntraDay>
) : StockIntraDayDataSource {
    override suspend fun getListStockIntraDay(symbol: String): List<StockIntraDay> {
        val responseBody = api.getListStockIntraDay(symbol)

        return csvParser.parse(responseBody.byteStream())
    }
}