package com.example.ixgsdkdemo.ui.screens.qrScanner

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun CameraView(
    viewModel: QRCodeViewModel,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .enableAutoZoom()
        .build()

    LaunchedEffect(Unit, block =  {
        val scanner = GmsBarcodeScanning.getClient(context, options)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                val rawValue: String? = barcode.rawValue
                val newValue = rawValue ?: ""
                Log.v("QR_SCAN_SCREEN", "Scan result: $newValue")
                if(!rawValue.isNullOrEmpty()) {
                    viewModel.sendQRCode(newValue)
                } else
                    Log.v("QR_SCAN_SCREEN", "String is NULL or EMPTY")
            }
            .addOnCanceledListener {
                // TODO: Show dialog to illustrate that they should scan the code
                Log.v("QR_SCAN_SCREEN", "Scanner Canceled")
            }
            .addOnFailureListener { e ->
                // TODO: Show dialog providing failure reason
                Log.v("QR_SCAN_SCREEN", "Scanner Failed: ${e.message}")
            }
    })
}