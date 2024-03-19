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
import com.example.ixgcore.IXGCore
import com.example.ixgsdkdemo.AppScreenRoute
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme


@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel,
) {

    val appName by viewModel.appName.collectAsState()
    val registerResult by viewModel.registerResult.collectAsState()

    if (registerResult != null) {
        registerResult!!.isSuccess
            navController.navigate(AppScreenRoute.StationsList.name)
    }

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
            viewModel.handleRegistrationClicked()
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
        val context = LocalContext.current
        val registrationManager = IXGCore(context).registrationManager
        val viewModel = RegistrationViewModel(
            registrationManager = registrationManager)
        RegistrationScreen(
            navController = navController,
            viewModel = viewModel,
        )
    }
}