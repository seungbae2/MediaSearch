package com.sb.mediasearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.sb.mediasearch.core.designsystem.theme.MsTheme
import com.sb.mediasearch.ui.MainScreen
import com.sb.mediasearch.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState(windowSizeClass = calculateWindowSizeClass(this))
            MsTheme {
                MainScreen(appState = appState)
            }
        }
    }
}
