package cvb.com.br.composestockmarket.domain.use_case

import android.util.Log
import cvb.com.br.composestockmarket.domain.model.Stock
import cvb.com.br.composestockmarket.domain.repository.StockRepository
import cvb.com.br.composestockmarket.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class QueryListStock @Inject constructor(private val repository: StockRepository) {
    companion object {
        val TAG = QueryListStock::class.simpleName
    }

    operator fun invoke(
        updateDataFromRemoteSource: Boolean = false,
        query: String
    ): Flow<Resource<List<Stock>>> {
        return flow {
            try {
                Log.i(TAG, "QueryListStock::Begin")

                emit(Resource.Loading())
                val localData = repository.queryListStock(query)
                Log.i(TAG, "QueryListStock::Local[records]->${localData.size}")

                val isLocalSourceEmpty = localData.isEmpty() && query.isBlank()
                Log.i(TAG, "QueryListStock::isLocalSourceEmpty->${isLocalSourceEmpty}")

                val loadRemoteData = isLocalSourceEmpty || updateDataFromRemoteSource
                Log.i(TAG, "QueryListStock::loadRemoteData->${loadRemoteData}")

                if (loadRemoteData) {
                    val remoteData = repository.getListStock()
                    Log.i(TAG, "QueryListStock::Remote[records]->${remoteData.size}")

                    if (remoteData.isEmpty()) {
                        Log.i(TAG, "QueryListStock::Error->There is no data from server to import!")
                        emit(Resource.Error(message = "App was not able to recover data from server!"))
                        return@flow
                    }

                    repository.deleteAllStock()
                    repository.insertListStock(remoteData)

                    val newLocalData = repository.queryListStock(query)
                    Log.i(TAG, "QueryListStock::Imported->${newLocalData.size}")

                    emit(Resource.Success(newLocalData))
                } else {
                    emit(Resource.Success(localData))
                }
                Log.i(TAG, "QueryListStock::End")
            } catch (e: HttpException) {
                Log.i(TAG, "QueryListStock::HttpException")
                emit(Resource.Error(message = "HttpException: ${e.message ?: "Unexpected"}"))
            } catch (e: IOException) {
                Log.i(TAG, "QueryListStock::IOException")
                emit(Resource.Error(message = "IOException: ${e.message ?: "Unexpected"}"))
            } catch (e: Exception) {
                Log.i(TAG, "QueryListStock::Exception")
                emit(Resource.Error(message = "Exception: ${e.message ?: "Unexpected"}"))
            }
        }
    }

}