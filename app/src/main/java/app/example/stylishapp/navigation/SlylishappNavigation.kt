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

@Composable
fun StylishappNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StylishappScreens.SigninScreen.name
    ) {
        composable(StylishappScreens.SigninScreen.name) {
            val signinViewModel = hiltViewModel<SigninViewModel>()
            SigninScreen(navController = navController, viewModel = signinViewModel)
        }

        composable(StylishappScreens.HomeScreen.name) {
            HomeScreen()
        }
    }
}