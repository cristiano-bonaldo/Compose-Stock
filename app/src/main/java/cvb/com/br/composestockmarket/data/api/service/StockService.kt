package cvb.com.br.composestockmarket.data.api.service

import cvb.com.br.composestockmarket.data.api.dto.StockInfoDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {
    //https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=LL75CC1A17HJ9HQG
    @GET("query?function=LISTING_STATUS")
    suspend fun getListStock(): ResponseBody

    //https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=60min&datatype=csv&apikey=LL75CC1A17HJ9HQG
    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getListStockIntraDay(@Query("symbol") symbol: String): ResponseBody

    //https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=LL75CC1A17HJ9HQG
    @GET("query?function=OVERVIEW")
    suspend fun getStockInfo(@Query("symbol") symbol: String): Response<StockInfoDto>
}