package cvb.com.br.composestockmarket.data.api.service

interface ApiService : StockService {

    companion object {
        const val BASE_URL = "https://www.alphavantage.co/"

        const val API_KEY = "LL75CC1A17HJ9HQG"
    }

}
