package cvb.com.br.composestockmarket.domain.use_case

import cvb.com.br.composestockmarket.domain.model.StockInfo
import cvb.com.br.composestockmarket.domain.repository.StockInfoRepository
import cvb.com.br.composestockmarket.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockInfo @Inject constructor(private val repository: StockInfoRepository) {
    suspend operator fun invoke(symbol: String): Resource<StockInfo> {
        return try {
            val stockInfo = repository.getStockInfo(symbol)
            Resource.Success(stockInfo)
        } catch (e: HttpException) {
            Resource.Error(message = "HttpException: ${e.message ?: "Unexpected"}")
        } catch (e: IOException) {
            Resource.Error(message = "IOException: ${e.message ?: "Unexpected"}")
        } catch (e: Exception) {
            Resource.Error(message = "Exception: ${e.message ?: "Unexpected"}")
        }
    }
}