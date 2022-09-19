package com.yonigofman.bluetoothscannerapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yonigofman.bluetoothscannerapp.ViewModel.DevicesViewModel
import com.yonigofman.bluetoothscannerapp.ui.theme.Background1
import com.yonigofman.bluetoothscannerapp.ui.theme.Background2
import com.yonigofman.bluetoothscannerapp.ui.theme.Background3

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: DevicesViewModel = DevicesViewModel()) {
        Column(modifier = modifier
            .fillMaxSize()
            .background(Background2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            Logo()
            DevicesList(viewModel)
        }
}