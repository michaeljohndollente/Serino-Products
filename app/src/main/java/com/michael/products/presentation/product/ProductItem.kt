package com.michael.products.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.michael.products.domain.Product
import com.michael.products.ui.theme.MyProductsTheme

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = product.images.first(),
                contentDescription = product.images.first(),
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = "\u20B1" + product.price,
                    modifier = Modifier
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    MyProductsTheme {
        ProductItem(
            product = Product(
                id = 1,
                title = "Product#1",
                description = "This is the Product #1",
                price = 10000,
                discountPercentage = 15.0,
                rating = 4.0,
                stock = 2,
                brand = "ProductBrand",
                category = "ProductCategory",
                thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
                images = listOf("https://cdn.dummyjson.com/product-images/1/thumbnail.jpg")
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}