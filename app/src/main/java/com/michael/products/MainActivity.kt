package com.michael.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.michael.products.presentation.details.DetailsScreen
import com.michael.products.presentation.product.ProductScreen
import com.michael.products.presentation.utils.Screen
import com.michael.products.ui.theme.MyProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProductsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ProductScreen.route
                    ) {
                        composable(route = Screen.ProductScreen.route) {
                            ProductScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.DetailsScreen.route + "?id={id}&title={title}&description={description}&price={price}&category={category}&brand={brand}&thumbnail={thumbnail}&images={images}",
                            arguments = listOf(
                                navArgument(
                                    name = "title"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "description"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "price"
                                ) {
                                    type = NavType.IntType
                                },
                                navArgument(
                                    name = "category"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "brand"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "thumbnail"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "images"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val productTitle = backStackEntry.arguments!!.getString("title")!!
                            val productDescription =
                                backStackEntry.arguments!!.getString("description")!!
                            val productPrice = backStackEntry.arguments!!.getInt("price")
                            val productCategory = backStackEntry.arguments!!.getString("category")!!
                            val productBrand = backStackEntry.arguments!!.getString("brand")!!
                            val productThumbnail =
                                backStackEntry.arguments!!.getString("thumbnail")!!
                            val jsonImages =
                                backStackEntry.arguments!!.getString("images")!!

                            val resultImages = mutableListOf<String>()

                            for (i in jsonImages.indices) {
                                if (jsonImages[i] == '[') {
                                    resultImages.add(jsonImages[i].toString())
                                }
                            }

                            DetailsScreen(
                                title = productTitle,
                                description = productDescription,
                                price = productPrice,
                                category = productCategory,
                                brand = productBrand,
                                thumbnail = productThumbnail,
                                images = resultImages
                            )
                        }
                    }
                }

            }
        }
    }
}
