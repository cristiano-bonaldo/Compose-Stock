package cvb.com.br.composestockmarket.data.data_source.remote

import cvb.com.br.composestockmarket.data.api.service.ApiService
import cvb.com.br.composestockmarket.data.csv.CSVParser
import cvb.com.br.composestockmarket.domain.data_source.StockDataSource
import cvb.com.br.composestockmarket.domain.model.Stock
import javax.inject.Inject

class StockRemoteDS @Inject constructor(
    private val api: ApiService,
    private val csvParser: CSVParser<Stock>
) : StockDataSource {
    override suspend fun insertListStock(listStock: List<Stock>) {
        // Not available for Remote
    }

    override suspend fun deleteAllStock() {
        // Not available for Remote
    }

    override suspend fun queryListStock(query: String): List<Stock> {
        // Not available for Remote
        return emptyList()
    }

    override suspend fun getListStock(): List<Stock> {
        val responseBody = api.getListStock()

        return csvParser.parse(responseBody.byteStream())
    }
}