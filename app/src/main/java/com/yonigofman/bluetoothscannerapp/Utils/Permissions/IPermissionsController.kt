package com.yonigofman.bluetoothscannerapp.Utils.Permissions

import androidx.activity.ComponentActivity

interface IPermissionsController {
    fun checkPermissions():Boolean
    fun requestPermissions(activity:ComponentActivity)
}