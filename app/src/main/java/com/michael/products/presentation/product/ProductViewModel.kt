package com.michael.products.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.michael.products.data.local.ProductEntity
import com.michael.products.data.mappers.toProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    pager: Pager<Int, ProductEntity>
) : ViewModel() {
    val productPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toProduct() }
        }
        .cachedIn(viewModelScope)
}
