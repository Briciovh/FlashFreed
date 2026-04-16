package com.softeen.flashfreed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.softeen.flashfreed.ui.auth.LoginScreen
import com.softeen.flashfreed.ui.theme.FlashFreedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashFreedTheme {
                LoginScreen(onLoginSuccess = { /* TODO: navigate to home */ })
            }
        }
    }
}
