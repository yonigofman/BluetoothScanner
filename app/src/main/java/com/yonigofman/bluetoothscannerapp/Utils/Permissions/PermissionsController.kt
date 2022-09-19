package com.yonigofman.bluetoothscannerapp.Utils.Permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.S)
class PermissionsController: IPermissionsController {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private val permissionsMap : MutableMap<String,Boolean> = mutableMapOf()
    private val permissionRequest : MutableList<String> = ArrayList()


    init {
        permissionsMap[Manifest.permission.BLUETOOTH_SCAN] = false
        permissionsMap[Manifest.permission.ACCESS_FINE_LOCATION] = false
        permissionsMap[Manifest.permission.BLUETOOTH_CONNECT] = false
    }

    override fun checkPermissions(): Boolean {
        return permissionsMap.all {
            it.value // checks if all permissions granted
        }
    }

    override fun requestPermissions(activity: ComponentActivity) {

        setLaunchPermissionRequest(activity)

        /**
         * checks every permission if is granted
         * and updated the map
         */
        permissionsMap.forEach {
                permissionsMap.replace(it.key,isPermissionGranted(activity,it.key))
        }

        //map of permission that denied
        val deniedMap = permissionsMap.filter {
            !it.value
        }
        Log.d("permissions","deniedMap: $deniedMap")

        deniedMap.forEach {
            addPermissionToRequest(it.key)
        }
        Log.d("permissions","permissionRequest: $permissionRequest")

        Log.d("permissions","permissionMap: $permissionsMap")


        if (permissionRequest.isNotEmpty()){
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }

    private fun addPermissionToRequest(permission:String){
        this.permissionRequest.add(permission)
    }

    private fun isPermissionGranted(activity: ComponentActivity,permission: String) = ContextCompat.checkSelfPermission(
        activity,
        permission
    ) == PackageManager.PERMISSION_GRANTED

    private fun setLaunchPermissionRequest(activity: ComponentActivity){
        permissionLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
            permissionsMap.forEach { permission ->
                permissionsMap.replace(permission.key,permissions[permission.key] ?: permission.value)
            }
        }

    }
}