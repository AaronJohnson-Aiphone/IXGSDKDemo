package com.example.ixgsdkdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IXGSDKDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(
                        appContext = applicationContext
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainActivityPreview() {
    IXGSDKDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current
            AppNavigation(
                appContext = context
            )
        }
    }
}