package com.yonigofman.bluetoothscannerapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.yonigofman.bluetoothscannerapp.Other.BTScanner
import com.yonigofman.bluetoothscannerapp.Utils.Permissions.IPermissionsController
import com.yonigofman.bluetoothscannerapp.Utils.Permissions.PermissionsController
import com.yonigofman.bluetoothscannerapp.ViewModel.DevicesViewModel
import com.yonigofman.bluetoothscannerapp.ui.components.HomeScreen
import com.yonigofman.bluetoothscannerapp.ui.theme.BluetoothScannerAppTheme

@RequiresApi(Build.VERSION_CODES.S)
class MainActivity :
    ComponentActivity(),
    IPermissionsController by PermissionsController() {

    private lateinit var scanner: BTScanner
    private val viewModel :DevicesViewModel = DevicesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setPermissions()
        setComposeContent()
        setScanner()


    }


    /**
     * sets the compose
     */
    private fun setComposeContent() {
        setContent {
            HomeScreen(viewModel = viewModel)
        }
    }


    private fun setPermissions(){
        if(!checkPermissions()){
            requestPermissions(this)
        }
    }


        private fun setScanner(){
           viewModel.setScanner(this)

        }

}


