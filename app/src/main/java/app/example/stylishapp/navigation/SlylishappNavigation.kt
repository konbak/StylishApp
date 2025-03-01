package app.example.stylishapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.example.domain.navigation.StylishappScreens
import app.example.home.HomeScreen
import app.example.signin.SigninScreen
import app.example.signin.SigninViewModel
import app.example.splash.SplashScreen
import app.example.splash.SplashScreenViewModel

@Composable
fun StylishappNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StylishappScreens.SplashScreen.name
    ) {
        composable(StylishappScreens.SigninScreen.name) {
            val signinViewModel = hiltViewModel<SigninViewModel>()
            SigninScreen(navController = navController, viewModel = signinViewModel)
        }

        composable(StylishappScreens.HomeScreen.name) {
            HomeScreen()
        }

        composable(StylishappScreens.SplashScreen.name) {
            val splashScreenViewModel = hiltViewModel<SplashScreenViewModel>()
            SplashScreen(navController = navController, viewModel = splashScreenViewModel)
        }
    }
}