package cvb.com.br.composestockmarket.di

import android.content.Context
import androidx.room.Room
import cvb.com.br.composestockmarket.data.api.interceptor.ApiInterceptor
import cvb.com.br.composestockmarket.data.api.service.ApiService
import cvb.com.br.composestockmarket.data.db.AppDataBase
import cvb.com.br.composestockmarket.data.db.dao.StockDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleProvide {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor
    ) =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    //=============

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideStockDao(appDatabase: AppDataBase): StockDao {
        return appDatabase.stockDao
    }
}

