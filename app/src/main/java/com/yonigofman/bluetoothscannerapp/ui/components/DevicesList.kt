package com.yonigofman.bluetoothscannerapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.yonigofman.bluetoothscannerapp.Model.BTDevice
import com.yonigofman.bluetoothscannerapp.ViewModel.DevicesViewModel
import com.yonigofman.bluetoothscannerapp.ui.theme.Background2
import com.yonigofman.bluetoothscannerapp.ui.theme.Green1
import com.yonigofman.bluetoothscannerapp.ui.theme.Green2
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview()
@Composable
fun DevicesList(viewModel: DevicesViewModel= DevicesViewModel()) {


    val listOfDevices = viewModel.devices.toList().map {
        it.second
    }

    val sorted = listOfDevices.sortedBy {
        it.distance
    }

    val isPlaying  = remember {
        mutableStateOf(false)
    }


    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        item() {
            MyButton("Scan") {
                viewModel.startScan()
                isPlaying.value = true
            }

            if(isPlaying.value){
                LaunchedEffect(Unit){
                    var time = 10
                    while(time > 0){
                        delay(1.seconds)
                        time--
                        println(time)
                    }
                    isPlaying.value = false
                }
                LottieScanAnimation(isPlaying = isPlaying.value)
            }

        }

        item {
            Text(text = "Scan ends in 10s", color = Color.White)
            Text(text = "(range = 1 meter)", color = Color.White)
        }


        items(sorted) {
            DeviceItem(it)
        }

    }
}


