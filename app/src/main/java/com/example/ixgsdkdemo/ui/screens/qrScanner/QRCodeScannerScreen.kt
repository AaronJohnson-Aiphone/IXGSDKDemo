package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ixgsdkdemo.ui.screens.common.ErrorScreen
import com.example.ixgsdkdemo.ui.screens.common.LoadingScreen
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme

@Composable
fun QRScannerScreen(
    qrScannerUiState: QRScannerUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (qrScannerUiState) {
        is QRScannerUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is QRScannerUiState.Success -> QRScanner(
            networkResponse = qrScannerUiState.photos,
            modifier = modifier.fillMaxSize())
        is QRScannerUiState.Error -> ErrorScreen(message = qrScannerUiState.errorMessage,
            modifier = modifier.fillMaxSize())
    }
}

@Composable
fun QRScanner(
    networkResponse: String,
    modifier: Modifier = Modifier)
{
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(
            textAlign = TextAlign.Center,
            text = networkResponse
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun QRScannerPreview_Success() {
    IXGSDKDemoTheme {
        Surface {
            QRScannerScreen(qrScannerUiState = QRScannerUiState.Success("Some string"))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun QRScannerPreview_Loading() {
    IXGSDKDemoTheme {
        Surface {
            QRScannerScreen(qrScannerUiState = QRScannerUiState.Loading)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun QRScannerPreview_Error() {
    IXGSDKDemoTheme {
        Surface {
            QRScannerScreen(qrScannerUiState = QRScannerUiState.Error("Failed to load"))
        }
    }
}