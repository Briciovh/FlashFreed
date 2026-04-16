package com.softeen.flashfreed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.softeen.flashfreed.data.analytics.AnalyticsHelper
import com.softeen.flashfreed.ui.auth.AuthViewModel
import com.softeen.flashfreed.ui.auth.LoginScreen
import com.softeen.flashfreed.ui.feed.CreatePostScreen
import com.softeen.flashfreed.ui.feed.FeedScreen
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NavGraphEntryPoint {
    fun analyticsHelper(): AnalyticsHelper
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val analyticsHelper = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            NavGraphEntryPoint::class.java
        ).analyticsHelper()
    }

    val currentUser by authViewModel.currentUser.collectAsStateWithLifecycle()
    val startDestination = if (currentUser != null) "feed" else "login"

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        currentRoute?.let { analyticsHelper.logScreenView(it) }
    }

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
                onCreatePostClick = { navController.navigate("create_post") }
            )
        }
        composable("create_post") {
            CreatePostScreen(
                onPostCreated = { navController.popBackStack() }
            )
        }
    }
}
