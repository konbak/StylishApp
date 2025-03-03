package app.example.home

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.example.domain.model.ProductDomain

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val activity = LocalActivity.current
    val products by viewModel.products.collectAsState()

    BackHandler(onBack = {
        activity?.moveTaskToBack(true)
    })

    Scaffold(
        content = { paddingValues ->
            HomeStateLessScreen(
                modifier = Modifier.padding(paddingValues),
                products = products,
            )
        }
    )
}

@Composable
internal fun HomeStateLessScreen(
    modifier: Modifier = Modifier,
    products: List<ProductDomain>,
){
    LazyRow {
        items(products) { product ->
            ProductItem(
                modifier = modifier,
                imageUrl = product.image,
                title = product.title,
                description = product.description,
                price = product.price.toString(),
            )
        }
    }
}