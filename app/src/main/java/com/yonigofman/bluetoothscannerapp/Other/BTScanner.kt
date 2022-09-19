package com.yonigofman.bluetoothscannerapp.Other

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import com.yonigofman.bluetoothscannerapp.Model.BTDevice
import com.yonigofman.bluetoothscannerapp.Utils.BTUtils
import com.yonigofman.bluetoothscannerapp.ViewModel.DevicesViewModel
import kotlinx.coroutines.*

@SuppressLint("MissingPermission")
data class BTScanner(
    private val activity: ComponentActivity,
    private val scanTime : Long,
    private val signalStrength: Int,
    private val viewModel:DevicesViewModel = DevicesViewModel(),

    ) {
    private  var bluetoothAdapter:BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    var isScanning: Boolean = false
         get() = field
         private set


    init {
        val bluetoothManager :BluetoothManager = this.activity
            .getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        bluetoothAdapter.enable()
    }


    fun start(){
        if(BTUtils.checkBluetooth(bluetoothAdapter)){
            scan(true)
        }
        else{
            Log.d("Scanner", "No Bluetooth!!")
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    private fun scan(enable:Boolean){

        if(enable && !isScanning) {

            Log.d("Scanner", "Starting Scanning...")

             GlobalScope.launch(Dispatchers.Default) {
                delay(scanTime)
                isScanning = false
                bluetoothAdapter.stopLeScan(scanCallback)
            }

            isScanning = true
            bluetoothAdapter.startLeScan(scanCallback)



        }
    }


    @SuppressLint("MissingPermission")
    private val scanCallback = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->

        val distance = BTUtils.rssiToDistance(rssi,signalStrength)
        val name = if (device.name != null){ device.name } else{ "NoName" }
        val address = device.address

        val btDevice = BTDevice(
            name = name,
            distance = distance,
            id = address
        )


        viewModel.addDevice(btDevice)


    }



}