package com.example.ixgsdkdemo.ui.screens.qrScanner

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


@Composable
fun QRScannerScreen(
    navController: NavController,
    modifier: Modifier
) {
    CameraView(viewModel = QRCodeViewModel())
}
@Composable
fun CameraView(
    viewModel: QRCodeViewModel
) {

    val context = LocalContext.current
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .enableAutoZoom()
        .build()

    val qrCode = remember { mutableStateOf("") }

    if (qrCode.value.isNullOrEmpty()) {
        Text(text = "Need to scan")
    } else {
        Text(text = "Found code: ${qrCode.value}")
    }


    LaunchedEffect(Unit, block =  {
        val scanner = GmsBarcodeScanning.getClient(context, options)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                // task completed successfully
                val rawValue: String? = barcode.rawValue
                val newValue = rawValue ?: ""
                Log.v("QR_SCAN_SCREEN", "Scan result: $newValue")
                if(!rawValue.isNullOrEmpty())
                    qrCode.value = newValue
                else
                    Log.v("QR_SCAN_SCREEN", "String is NULL or EMPTY")
            }
            .addOnCanceledListener {
                // task canceled
                // TODO: Show dialog to illustrate that they should scan the code
                Log.v("QR_SCAN_SCREEN", "Scanner Canceled")
            }
            .addOnFailureListener { e ->
                // task failed with an exception
                //viewModel.errorMessage = e.message.toString()
                // TODO: Show dialog providing failure reason
                Log.v("QR_SCAN_SCREEN", "Scanner Failed: ${e.message}")
            }

//        val registrationManager = RegistrationManager(context)
//        val result = registrationManager.sendQRCode("SJq03d!CKr!}=:$<'x@L,WV\\")
//        if (result.isSuccess) {
//            val name: String? = result.getOrNull()
//            Log.d("QRCodeScannerScreen", "result body: $name")
//        } else {
//            // Handle error
//        }
    })
}

@Preview(showSystemUi = true)
@Composable
private fun QRScannerPreview() {
    IXGSDKDemoTheme {
        Surface {
            val navController = rememberNavController()
            QRScannerScreen(navController = navController,
                modifier = Modifier.fillMaxSize())
        }
    }
}