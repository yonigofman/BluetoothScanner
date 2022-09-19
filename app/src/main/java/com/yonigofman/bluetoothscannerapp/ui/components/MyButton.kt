package com.yonigofman.bluetoothscannerapp.ui.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yonigofman.bluetoothscannerapp.ui.theme.Background1
import com.yonigofman.bluetoothscannerapp.ui.theme.Green1

@Composable
fun MyButton(text:String = "click",onClick : () ->Unit) {
    
    Button(
        onClick = { onClick() },
        colors =ButtonDefaults.buttonColors(backgroundColor = Green1) ) {

        Text(
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        
    }
    
}