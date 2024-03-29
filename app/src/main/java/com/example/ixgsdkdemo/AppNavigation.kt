package com.example.ixgsdkdemo

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ixgcore.IXGCore
import com.example.ixgsdkdemo.ui.screens.qrScanner.QRCodeViewModel
import com.example.ixgsdkdemo.ui.screens.qrScanner.QRScannerScreen
import com.example.ixgsdkdemo.ui.screens.registration.RegistrationScreen
import com.example.ixgsdkdemo.ui.screens.registration.RegistrationViewModel
import com.example.ixgsdkdemo.ui.screens.stationsList.StationsListScreen
import com.example.ixgsdkdemo.ui.screens.stationsList.StationsListViewModel

@Composable
fun AppNavigation(
    appContext: Context
) {
    val navController = rememberNavController()
    val ixgCore = IXGCore(appContext)

    NavHost(
        startDestination = AppScreenRoute.QRScanner.name,
        navController = navController
    ) {

        composable(AppScreenRoute.QRScanner.name) {
            val qrCodeViewModel: QRCodeViewModel by remember {
                mutableStateOf(QRCodeViewModel(registrationManager = ixgCore.registrationManager))
            }

            QRScannerScreen(
                navController = navController,
                viewModel = qrCodeViewModel,
                state = qrCodeViewModel.uiState,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(AppScreenRoute.Registration.name) {

            val registrationViewModel: RegistrationViewModel by remember {
                mutableStateOf(RegistrationViewModel(registrationManager = ixgCore.registrationManager))
            }
            RegistrationScreen(
                navController = navController,
                viewModel = registrationViewModel
            )
        }

        composable(AppScreenRoute.StationsList.name) {
            val stationsListViewModel: StationsListViewModel by remember {
                mutableStateOf(StationsListViewModel(stationsManager = ixgCore.stationsManager))
            }
            StationsListScreen(
                navController = navController,
                viewModel = stationsListViewModel
            )
        }
    }
}