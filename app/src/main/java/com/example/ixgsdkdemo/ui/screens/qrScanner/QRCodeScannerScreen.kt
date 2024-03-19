package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ixgcore.IXGCore
import com.example.ixgsdkdemo.AppScreenRoute
import com.example.ixgsdkdemo.ui.screens.common.ErrorScreen
import com.example.ixgsdkdemo.ui.screens.common.LoadingScreen
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme

@Composable
fun QRScannerScreen(
    navController: NavController,
    viewModel: QRCodeViewModel,
    state: QRCodeScreenState,
    modifier: Modifier = Modifier,
) {

    when (state) {
        QRCodeScreenState.Scanning -> CameraView(viewModel = viewModel, modifier = Modifier.fillMaxSize())
        QRCodeScreenState.Loading -> LoadingScreen(message = "Sending QR Code...")
        QRCodeScreenState.Finished -> navController.navigate(AppScreenRoute.Registration.name)
        QRCodeScreenState.Error -> ErrorScreen(message = "Error...")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QRScannerPreview_Loading() {
    IXGSDKDemoTheme {
        Surface {
            val navController = rememberNavController()
            val context = LocalContext.current
            val registrationManager = IXGCore(context).registrationManager
            val viewModel = QRCodeViewModel(registrationManager = registrationManager)
            QRScannerScreen(
                navController = navController,
                viewModel = viewModel,
                state = QRCodeScreenState.Loading)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QRScannerPreview_Scanning() {
    IXGSDKDemoTheme {
        Surface {
            val navController = rememberNavController()
            val context = LocalContext.current
            val registrationManager = IXGCore(context).registrationManager
            val viewModel = QRCodeViewModel(registrationManager = registrationManager)
            QRScannerScreen(
                navController = navController,
                viewModel = viewModel,
                state = QRCodeScreenState.Scanning,
                modifier = Modifier.fillMaxSize())
        }
    }
}