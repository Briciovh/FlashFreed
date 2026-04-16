package com.softeen.flashfreed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.softeen.flashfreed.ui.auth.AuthViewModel
import com.softeen.flashfreed.ui.auth.LoginScreen
import com.softeen.flashfreed.ui.feed.CreatePostScreen
import com.softeen.flashfreed.ui.profile.ProfileScreen

private object Routes {
    const val LOGIN = "login"
    const val PROFILE = "profile"
    const val CREATE_POST = "create_post"
}

@Composable
fun AppNavGraph(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val startDestination =
        if (authViewModel.currentUser.value != null) Routes.PROFILE else Routes.LOGIN
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.PROFILE) {
            ProfileScreen(
                onCreatePost = { navController.navigate(Routes.CREATE_POST) }
            )
        }
        composable(Routes.CREATE_POST) {
            CreatePostScreen(
                onPostCreated = { navController.popBackStack() }
            )
        }
    }
}
