package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ixgsdkdemo.AppScreenRoute
import com.example.ixgsdkdemo.ui.screens.common.LoadingScreen
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme

sealed interface QRCodeScreenState {
    object Scanning: QRCodeScreenState
    object Loading: QRCodeScreenState
    object Finished: QRCodeScreenState
}

@Composable
fun QRScannerScreen(
    navController: NavController,
    viewModel: QRCodeViewModel,
    state: QRCodeScreenState,
    modifier: Modifier = Modifier,
) {
    val roomCode by viewModel.qrCode.collectAsState()
    val appName = "My Intercom App"

    when (state) {
        is QRCodeScreenState.Scanning -> CameraView(viewModel = viewModel, modifier = Modifier.fillMaxSize())
        is QRCodeScreenState.Loading -> LoadingScreen(message = "Sending QR Code...")
        is QRCodeScreenState.Finished -> navController.navigate(AppScreenRoute.Registration.name + "/${roomCode}/${appName}")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QRScannerPreview() {
    IXGSDKDemoTheme {
        Surface {
            val navController = rememberNavController()
            val viewModel = QRCodeViewModel()
            QRScannerScreen(
                navController = navController,
                viewModel = viewModel,
                state = QRCodeScreenState.Scanning,
                modifier = Modifier.fillMaxSize())
        }
    }
}