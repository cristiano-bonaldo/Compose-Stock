package cvb.com.br.composestockmarket.data.api.interceptor

import cvb.com.br.composestockmarket.data.api.service.ApiService
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        /*
        val newUrl = originalHttpUrl.newBuilder()
            .addPathSegment(ApiService.API_KEY)
            .build()
        */

        /*
        Exemplo com Query Parameter
        --
        */
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", ApiService.API_KEY)
            .build()

        val requestBuilder = original.newBuilder().url(newUrl)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}