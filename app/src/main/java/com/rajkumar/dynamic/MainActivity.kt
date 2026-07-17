package com.rajkumar.dynamic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajkumar.dynamic.navigation.NavigationManager
import com.rajkumar.dynamic.theme.BankingTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var rendererFactory: com.rajkumar.dynamic.core.renderer.RendererFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                navigationManager.commands.collect { route ->
                    navController.navigate(route)
                }
            }

            BankingTheme {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        DynamicScreen(screenId = "home", rendererFactory = rendererFactory)
                    }
                    composable("balance_details") {
                        DynamicScreen(screenId = "balance", rendererFactory = rendererFactory)
                    }
                    composable("transaction_history") {
                        DynamicScreen(screenId = "statement", rendererFactory = rendererFactory)
                    }
                    composable("analytics") {
                        DynamicScreen(screenId = "analytics", rendererFactory = rendererFactory)
                    }
                    composable("pay_bills") {
                        DynamicScreen(screenId = "pay_bills", rendererFactory = rendererFactory)
                    }
                    composable("send_money") {
                        DynamicScreen(screenId = "send_money", rendererFactory = rendererFactory)
                    }
                    composable("scan_qr") {
                        DynamicScreen(screenId = "scan_qr", rendererFactory = rendererFactory)
                    }
                    composable("admin") {
                        AdminScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
