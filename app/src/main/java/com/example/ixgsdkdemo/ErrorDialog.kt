package com.example.ixgsdkdemo

import android.app.Activity
import android.content.IntentSender.OnFinished
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme

@Composable
fun ErrorDialog(
    errorTitle: String,
    errorMessage: String,
    onFinished: () -> Unit,
    activity: Activity,
    modifier: Modifier = Modifier,
) {

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = errorTitle) },
        text = { Text(text = errorMessage) },
        modifier = modifier,

        confirmButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "OK")
            }
        }
    )
}

@Preview()
@Composable
fun FinalScoreDialogPreview() {
    IXGSDKDemoTheme {
        ErrorDialog(
            errorTitle = "Error Title",
            errorMessage = "Description here",
            onFinished = {},
            activity = Activity()
        )
    }
}