package cvb.com.br.composestockmarket.domain.use_case

import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import cvb.com.br.composestockmarket.domain.repository.StockIntraDayRepository
import cvb.com.br.composestockmarket.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListStockIntraDay @Inject constructor(private val repository: StockIntraDayRepository) {

    suspend operator fun invoke(symbol: String): Resource<List<StockIntraDay>> {
        return try {
            val listStockIntraDay = repository.getListStockIntraDay(symbol)
            Resource.Success(listStockIntraDay)
        } catch (e: HttpException) {
            Resource.Error(message = "HttpException: ${e.message ?: "Unexpected"}")
        } catch (e: IOException) {
            Resource.Error(message = "IOException: ${e.message ?: "Unexpected"}")
        } catch (e: Exception) {
            Resource.Error(message = "Exception: ${e.message ?: "Unexpected"}")
        }
    }

}