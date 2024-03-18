package com.example.ixgsdkdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ixgsdkdemo.ui.screens.qrScanner.QRCodeViewModel
import com.example.ixgsdkdemo.ui.screens.qrScanner.QRScannerScreen
import com.example.ixgsdkdemo.ui.screens.registration.RegistrationScreen

@Composable
fun AppNavigation(
    activity: MainActivity
) {
    val navController = rememberNavController()
    NavHost(
        startDestination = AppScreenRoute.QRScanner.name,
        navController = navController
    ) {

        composable(AppScreenRoute.QRScanner.name) {
            val viewModel: QRCodeViewModel = viewModel()
            QRScannerScreen(
                navController = navController,
                viewModel = viewModel,
                state = viewModel.uiState,
                modifier = Modifier.fillMaxSize())
        }

        composable(AppScreenRoute.Registration.name + "/{roomCode}/{appName}",
            arguments = listOf(
                navArgument("roomCode") { type = NavType.StringType},
                navArgument("appName") { type = NavType.StringType }
            )) {

            RegistrationScreen(
                viewModel = viewModel(),
                activity = activity
                )
        }
    }
}