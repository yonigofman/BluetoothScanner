package com.yonigofman.bluetoothscannerapp.Utils

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.text.DecimalFormat
import kotlin.math.pow

object BTUtils {


    fun checkBluetooth(bluetoothAdapter: BluetoothAdapter?):Boolean{
        if( bluetoothAdapter == null || !bluetoothAdapter.isEnabled)
            return false
         return true
    }


    fun rssiToDistance(rssi: Int,signalStrength:Int): Double {
        return 10.0.pow((1.0 * (signalStrength - rssi)) / (10 * 2)).format(2)
    }

   private fun Double.format(fracDigits: Int): Double {
        val df = DecimalFormat()
        df.maximumFractionDigits = fracDigits
        return df.format(this).toDouble()
    }


}