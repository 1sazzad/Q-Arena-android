package com.reviseq.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.reviseq.android.presentation.navigation.AppNavigation
import com.reviseq.android.ui.theme.ReviseQTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviseQTheme {
                AppNavigation()
            }
        }
    }
}
