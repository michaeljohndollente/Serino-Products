package com.michael.products.presentation.product

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.michael.products.domain.Product

@Composable
fun ProductScreen(
    products: LazyPagingItems<Product>,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = products.loadState) {
        if (products.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (products.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (products.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(0.95F)
                    .padding(top = 20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

            ) {

                Text(
                    text = "Products",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.displayMedium
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(0.95F)
                    .align(Alignment.Center)
                    .padding(top = 70.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    products.itemCount
                ) { index ->
                    products[index]?.let {
                        ProductItem(
                            product = Product(
                                id = it.id,
                                title = it.title,
                                description = it.description,
                                price = it.price,
                                discountPercentage = it.discountPercentage,
                                rating = it.rating,
                                stock = it.stock,
                                brand = it.brand,
                                category = it.category,
                                thumbnail = it.thumbnail,
                                images = it.images
                            )
                        )
                    }
                }
                item {
                    if (products.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}