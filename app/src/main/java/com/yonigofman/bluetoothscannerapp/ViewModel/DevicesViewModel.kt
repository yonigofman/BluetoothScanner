package com.yonigofman.bluetoothscannerapp.ViewModel

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.yonigofman.bluetoothscannerapp.Model.BTDevice
import com.yonigofman.bluetoothscannerapp.Other.BTScanner

class DevicesViewModel : ViewModel() {

    private lateinit var scanner: BTScanner


    private  val _devices = mutableStateMapOf<String, BTDevice>()
    val devices: Map<String, BTDevice> = _devices





    fun addDevice(device: BTDevice) {
        if (!_devices.containsKey(device.id)) {
            _devices.put(device.id, device)
        } else {
            Log.d("test", "before: ${_devices.get(device.id)!!.distance}")
            _devices.get(device.id)!!.distance = device.distance
            Log.d("test", "after: ${_devices.get(device.id)!!.distance}")
        }



        _devices.forEach {
            Log.d(
                "devices",
                "id: ${it.value.id} | name: ${it.value.name} | distance ${it.value.distance}"
            )
        }


    }


    fun startScan(){
        scanner.start()
    }


    fun setScanner(activity: ComponentActivity){
         scanner = BTScanner(activity,5000,-75,this)
    }



}