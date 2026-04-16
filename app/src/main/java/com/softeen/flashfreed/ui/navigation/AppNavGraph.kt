package com.softeen.flashfreed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.softeen.flashfreed.ui.auth.AuthViewModel
import com.softeen.flashfreed.ui.auth.LoginScreen
import com.softeen.flashfreed.ui.feed.CreatePostScreen
import com.softeen.flashfreed.ui.feed.FeedScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val currentUser by authViewModel.currentUser.collectAsStateWithLifecycle()
    val startDestination = if (currentUser != null) "feed" else "login"

    NavHost(navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("feed") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("feed") {
            FeedScreen(
                onCreatePostClick = {
                    navController.navigate("create_post")
                }
            )
        }
        composable("create_post") {
            CreatePostScreen(
                onPostCreated = { navController.popBackStack() }
            )
        }
    }
}
