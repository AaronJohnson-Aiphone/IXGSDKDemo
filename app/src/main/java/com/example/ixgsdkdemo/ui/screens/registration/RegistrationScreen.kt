package com.example.ixgsdkdemo.ui.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ixgcore.RegistrationManager
import com.example.ixgsdkdemo.AppScreenRoute
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme


@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel,
) {

    val appName by viewModel.appName.collectAsState()

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Enter Name",
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
        )

        OutlinedTextField(
            value = appName,
            onValueChange = { text ->
                viewModel.appName.value = text
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )

        Button(onClick = {
            val success = viewModel.handleRegistrationClicked()
            if(success) {
                navController.navigate(AppScreenRoute.StationsList.name)
            }
            else {
                // TODO: Replace with dialog
                println("API Call failed")
            }
        },
            modifier = Modifier
                .padding(64.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = "Register")
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {

    IXGSDKDemoTheme {
        val navController = rememberNavController()
//        val stateHandle = SavedStateHandle()
//        stateHandle["roomCode"] = "abcde1234"
//        stateHandle["appName"] = "Intercom App"
        val context = LocalContext.current
        val registrationManager = RegistrationManager(context)
        val viewModel = RegistrationViewModel(
            registrationManager = registrationManager)
        RegistrationScreen(
            navController = navController,
            viewModel = viewModel,
        )
    }
}