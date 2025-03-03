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
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

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
                price = formatPrice(product.price),
            )
        }
    }
}

private fun formatPrice(price: Double): String {
    val locale = Locale.getDefault()
    val currencyFormat = NumberFormat.getCurrencyInstance(locale)

    currencyFormat.currency = Currency.getInstance("EUR")

    return if (price % 1 == 0.0) {
        val formattedPrice = NumberFormat.getNumberInstance(locale).format(price.toInt())
        currencyFormat.format(formattedPrice.toDouble()).replace(",00", "").replace(".00", "")
    } else {
        currencyFormat.format(price)
    }
}
