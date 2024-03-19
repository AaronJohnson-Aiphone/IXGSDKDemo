package com.example.ixgsdkdemo.ui.screens.stationsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ixgsdkdemo.ui.theme.IXGSDKDemoTheme

@Composable
fun StationsListScreen(
    navController: NavController,
    viewModel: StationsListViewModel,
    modifier: Modifier = Modifier
) {
    // TODO: implement taps
    if (viewModel.errorMessage.isEmpty()) {
        LazyColumn {
            items(viewModel.stationsList) { station ->
                StationItemRow(station = station)
                HorizontalDivider()
            }
        }
    } else {
        // TODO: show error dialog
        Text(viewModel.errorMessage)
    }
}

@Preview(showSystemUi = true)
@Composable
fun StationsListViewPreview() {
    IXGSDKDemoTheme {
        val navController = rememberNavController()
        Surface {
            StationsListScreen(navController, StationsListViewModel())
        }
    }
}

@Composable
fun StationItemRow(station: IXGStation) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(4.dp, 8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = station.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = station.description,
                fontStyle = FontStyle.Italic
            )
        }
    }
}