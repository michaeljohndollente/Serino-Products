package com.michael.products.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.michael.products.data.local.ProductDatabase
import com.michael.products.data.local.ProductEntity
import com.michael.products.data.remote.ProductApi
import com.michael.products.data.remote.ProductRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "products.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductPager(
        productDb: ProductDatabase,
        productApi: ProductApi
    ): Pager<Int, ProductEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = ProductRemoteMediator(
                productDb = productDb,
                productApi = productApi
            ),
            pagingSourceFactory = {
                productDb.dao.pagingSource()
            }
        )
    }
}