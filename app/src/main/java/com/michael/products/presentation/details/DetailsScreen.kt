package com.michael.products.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsScreen(
    title: String,
    description: String,
    price: Int,
    category: String,
    brand: String,
    thumbnail: String,
    images: MutableList<String>,

    ) {
    Box(modifier = Modifier.fillMaxSize()) {

        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(0.95F)
                .padding(top = 20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {



            Text(
                text = "Details",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = thumbnail,
                    contentDescription = thumbnail,
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
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                }

                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = "\u20B1" + price,
                        modifier = Modifier
                            .align(Alignment.End)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(16.dp)
            ) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(16.dp)
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = brand,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(images.size) {
                        ImageItem(it.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}