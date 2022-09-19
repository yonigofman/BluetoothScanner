package com.yonigofman.bluetoothscannerapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yonigofman.bluetoothscannerapp.Model.BTDevice
import com.yonigofman.bluetoothscannerapp.ViewModel.DevicesViewModel
import com.yonigofman.bluetoothscannerapp.ui.theme.Background2
import kotlin.random.Random

@Preview()
@Composable
fun DevicesList(viewModel: DevicesViewModel= DevicesViewModel()) {


    val listOfDevices = viewModel.devices.toList().map {
        it.second
    }

    val sorted = listOfDevices.sortedBy {
        it.distance
    }

    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        
        item(){
            MyButton("Scan") {
                viewModel.startScan()
            }
        }
        
        item {
            Text(text = "Scan end every 10s", color = Color.White)
            Text(text = "(range = 1 meter)", color = Color.White)
        }




        items(sorted){
            DeviceItem(it)
        }

    }
}


