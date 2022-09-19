package com.yonigofman.bluetoothscannerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yonigofman.bluetoothscannerapp.ui.theme.Background1
import com.yonigofman.bluetoothscannerapp.ui.theme.Green1
import com.yonigofman.bluetoothscannerapp.ui.theme.Green2
import com.yonigofman.bluetoothscannerapp.ui.theme.Shapes

@Preview
@Composable
fun Logo(title:String = "BluetoothScanner") {

    Card(
        backgroundColor = Background1,
        elevation = 15.dp,
        shape = Shapes.small,
        border = BorderStroke(2.dp, Green2)

    ) {

        Text(
            text = title,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(5.dp)
        )

    }

}
