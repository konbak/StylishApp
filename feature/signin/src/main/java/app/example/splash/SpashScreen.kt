package app.example.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.example.domain.navigation.StylishappScreens

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val token by viewModel.token.collectAsState()

    LaunchedEffect(token) {
        if (token != null) {
            navController.navigate(StylishappScreens.HomeScreen.name) {
                popUpTo(StylishappScreens.SplashScreen.name) { inclusive = true }
            }
        } else {
            navController.navigate(StylishappScreens.SigninScreen.name) {
                popUpTo(StylishappScreens.SplashScreen.name) { inclusive = true }
            }
        }
    }

    Scaffold (
        content = { paddingValues ->
            SplashStatelessScreen(
                modifier = Modifier.padding(paddingValues),
            )
        }
    )
}

@Composable
private fun SplashStatelessScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Logo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    SplashStatelessScreen()
}