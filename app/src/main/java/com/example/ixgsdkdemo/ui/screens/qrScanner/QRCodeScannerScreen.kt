package com.example.ixgsdkdemo.ui.screens.qrScanner

import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ixgcore.RegistrationManager
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions


@Composable
fun QRScannerScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    QRScanner(
        modifier = modifier.fillMaxSize())
}

@Composable
fun QRScanner(
    modifier: Modifier = Modifier)
{
//    Column (
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = modifier
//    ){
//        Text(
//            textAlign = TextAlign.Center,
//            text = networkResponse
//        )
//    }
    CameraView(viewModel = QRCodeViewModel())
}

@Composable
fun CameraView(viewModel: QRCodeViewModel) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    AndroidView(modifier = Modifier
        .fillMaxSize(),
        factory = { mContext ->
        PreviewView(mContext).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setBackgroundColor(0)
            scaleType = PreviewView.ScaleType.FILL_START
        }.also { previewView ->
            previewView.controller = cameraController
            cameraController.bindToLifecycle(lifecycleOwner)
        }
    })
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .enableAutoZoom()
        .build()

    var qrCode = ""

    LaunchedEffect(Unit, block =  {
//        val scanner = GmsBarcodeScanning.getClient(context, options)
//        scanner.startScan()
//            .addOnSuccessListener { barcode ->
//                // task completed successfully
//                val rawValue: String? = barcode.rawValue
//                Log.v("QR_SCAN_SCREEN", "Scan result: $rawValue")
//                if(!rawValue.isNullOrEmpty())
//                    qrCode = rawValue
//                else
//                    Log.v("QR_SCAN_SCREEN", "String is NULL or EMPTY")
//            }
//            .addOnCanceledListener {
//                // task canceled
//                Log.v("QR_SCAN_SCREEN", "Scanner Canceled")
//            }
//            .addOnFailureListener { e ->
//                // task failed with an exception
//                //viewModel.errorMessage = e.message.toString()
//                Log.v("QR_SCAN_SCREEN", "Scanner Failed: ${e.message}")
//            }

        val registrationManager = RegistrationManager(context)
        val result = registrationManager.sendQRCode("SJq03d!CKr!}=:$<'x@L,WV\\")
        if (result.isSuccess) {
            val name: String? = result.getOrNull()
            Log.d("QRCodeScannerScreen", "result body: $name")
        } else {
            // Handle error
        }
    })
}