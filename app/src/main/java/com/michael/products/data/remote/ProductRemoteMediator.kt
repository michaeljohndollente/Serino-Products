package com.michael.products.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.michael.products.data.local.ProductDatabase
import com.michael.products.data.local.ProductEntity
import com.michael.products.data.mappers.toProductEntity
import kotlinx.coroutines.delay
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val productDb: ProductDatabase,
    private val productApi: ProductApi
) : RemoteMediator<Int, ProductEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1
                    else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            delay(2000L)
            val products = productApi.getProducts(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            productDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    productDb.dao.clearCache()
                }
                val productEntities = products.products.map { it.toProductEntity() }
                productDb.dao.upsertAll(productEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = products.products.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}