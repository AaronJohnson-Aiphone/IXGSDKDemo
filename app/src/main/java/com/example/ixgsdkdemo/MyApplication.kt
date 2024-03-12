package com.example.ixgsdkdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ixgsdkdemo.ui.screens.qrScanner.QRCodeViewModel
import com.example.ixgsdkdemo.ui.screens.qrScanner.QRScannerScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val qrCodeViewModel: QRCodeViewModel = viewModel()
            QRScannerScreen(
                qrScannerUiState = qrCodeViewModel.qrUiState,
                contentPadding = it,
            )
        }
    }
}