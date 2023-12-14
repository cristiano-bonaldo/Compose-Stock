package cvb.com.br.composestockmarket.di

import cvb.com.br.composestockmarket.data.csv.CSVParser
import cvb.com.br.composestockmarket.data.csv.StockCSVParser
import cvb.com.br.composestockmarket.data.csv.StockIntraDayCSVParser
import cvb.com.br.composestockmarket.data.data_source.local.StockLocalDS
import cvb.com.br.composestockmarket.data.data_source.remote.StockInfoRemoteDS
import cvb.com.br.composestockmarket.data.data_source.remote.StockIntraDayRemoteDS
import cvb.com.br.composestockmarket.data.data_source.remote.StockRemoteDS
import cvb.com.br.composestockmarket.data.repository.StockInfoRepositoryImpl
import cvb.com.br.composestockmarket.data.repository.StockIntraDayRepositoryImpl
import cvb.com.br.composestockmarket.data.repository.StockRepositoryImpl
import cvb.com.br.composestockmarket.domain.data_source.StockDataSource
import cvb.com.br.composestockmarket.domain.data_source.StockInfoDataSource
import cvb.com.br.composestockmarket.domain.data_source.StockIntraDayDataSource
import cvb.com.br.composestockmarket.domain.model.Stock
import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import cvb.com.br.composestockmarket.domain.repository.StockInfoRepository
import cvb.com.br.composestockmarket.domain.repository.StockIntraDayRepository
import cvb.com.br.composestockmarket.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBind {

    @Binds
    @Singleton
    abstract fun bindStockCSVParser(csvParser: StockCSVParser): CSVParser<Stock>

    @Binds
    @Singleton
    abstract fun bindStockIntraDayCSVParser(csvParser: StockIntraDayCSVParser): CSVParser<StockIntraDay>

    @StockLocalDataSource
    @Singleton
    @Binds
    abstract fun bindStockLocalDataSource(dataSource: StockLocalDS): StockDataSource

    @StockRemoteDataSource
    @Singleton
    @Binds
    abstract fun bindStockRemoteDataSource(dataSource: StockRemoteDS): StockDataSource

    @Binds
    @Singleton
    abstract fun bindStockRepository(repository: StockRepositoryImpl): StockRepository

    @Singleton
    @Binds
    abstract fun bindStockInfoRemoteDataSource(dataSource: StockInfoRemoteDS): StockInfoDataSource

    @Binds
    @Singleton
    abstract fun bindStockInfoRepository(repository: StockInfoRepositoryImpl): StockInfoRepository

    @Singleton
    @Binds
    abstract fun bindStockIntraDayRemoteDataSource(dataSource: StockIntraDayRemoteDS): StockIntraDayDataSource

    @Binds
    @Singleton
    abstract fun bindStockIntraDayRepository(repository: StockIntraDayRepositoryImpl): StockIntraDayRepository
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StockLocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StockRemoteDataSource
