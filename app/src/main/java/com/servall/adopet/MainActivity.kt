package com.servall.adopet

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
import com.servall.adopet.ui.AdoptBottomNavigation
import com.servall.adopet.ui.PetsContent
import com.servall.adopet.ui.favorites.FavoritesScreen
import com.servall.adopet.ui.pets.PetDetail
import com.servall.adopet.ui.profile.ForgotPassword
import com.servall.adopet.ui.profile.Profile
import com.servall.adopet.ui.theme.AdopetTheme

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
                composable(Routes.Favorites.route) {
                    FavoritesScreen(
                        modifier = Modifier.padding(padding),
                        navigateUp = navController::navigateUp
                    )
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
        PetsContent(
            modifier = modifier,
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AdopetTheme {
        AdoptApp()
    }
}