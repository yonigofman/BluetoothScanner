package com.yonigofman.bluetoothscannerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yonigofman.bluetoothscannerapp.Model.BTDevice
import com.yonigofman.bluetoothscannerapp.ui.theme.Background1
import com.yonigofman.bluetoothscannerapp.ui.theme.Green1
import com.yonigofman.bluetoothscannerapp.ui.theme.Green2
import com.yonigofman.bluetoothscannerapp.ui.theme.Shapes
import kotlin.random.Random

@Preview()
@Composable
fun DeviceItem(device:BTDevice = defaultDevice()) {

    Card(
        backgroundColor = Background1,
        elevation = 15.dp,
        shape = Shapes.small,
        border = BorderStroke(2.dp, Green2)

    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Name: ${device.name}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
                )
            Text(
                text = "Id: ${device.id}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Distance: ${device.distance}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
            )


            if(device.distance <= 1){
                Text(
                    text = "In Range",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green1
                )
            }
            else{
                Text(
                    text = "Out of Range",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }


        }
    }
}

fun defaultDevice():BTDevice{
    return BTDevice(
        name = "UnK",
        distance = Random.nextDouble(30.0),
        id = "${Random.nextInt(0,9)}A: " +
                "C:${Random.nextInt(0,9)}" +
                ":${Random.nextInt(0,9)}G"
    )
}
