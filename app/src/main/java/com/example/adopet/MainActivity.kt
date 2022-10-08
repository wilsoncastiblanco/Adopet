package com.example.adopet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.adopet.ui.AdoptBottomNavigation
import com.example.adopet.ui.AdoptContent
import com.example.adopet.ui.pets.PetDetail
import com.example.adopet.ui.profile.ForgotPassword
import com.example.adopet.ui.profile.Profile
import com.example.adopet.ui.theme.AdopetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdopetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { AdoptApp() }
            }
        }
    }
}

@Composable
fun AdoptApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            AdoptBottomNavigation(
                destination = currentScreen,
                onSelectNavigation = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        content = { padding ->
            NavHost(navController = navController, startDestination = Routes.Home.route) {
                pets(navController, Modifier.padding(padding))
                profile(navController)
                composable(Routes.Favorites.route) {
                    Text("Favorites!!")
                }
                composable(Routes.Notifications.route) {
                    Text("You have 0 notifications")
                }
            }
        }
    )
}

private fun NavGraphBuilder.pets(
    navController: NavController,
    modifier: Modifier
) {
    composable(Routes.Home.route) {
        AdoptContent(
            modifier,
            openPetDetail = { petId ->
                navController.navigate(InternalRoutes.PetDetail.createRoute(petId))
            }
        )
    }
    composable(
        route = InternalRoutes.PetDetail.createRoute(),
        arguments = listOf(navArgument("petId") { type = NavType.StringType })
    ) { backStackEntry ->
        PetDetail(
            navigateUp = navController::navigateUp,
            petId = backStackEntry.arguments?.getString("petId")!!
        )
    }
}

private fun NavGraphBuilder.profile(
    navController: NavController,
) {
    composable(Routes.Profile.route) {
        Profile(
            openForgotPassword = {
                navController.navigate(InternalRoutes.ForgotPassword.createRoute())
            }
        )
    }
    composable(InternalRoutes.ForgotPassword.createRoute()) {
        ForgotPassword(
            navigateUp = navController::navigateUp
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AdopetTheme {
        AdoptApp()
    }
}