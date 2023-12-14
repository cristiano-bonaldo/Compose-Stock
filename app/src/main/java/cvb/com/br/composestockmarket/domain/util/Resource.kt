package cvb.com.br.composestockmarket.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null, isLoading: Boolean = true) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
}