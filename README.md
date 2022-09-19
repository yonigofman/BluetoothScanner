# BluetoothScanner

![image](https://user-images.githubusercontent.com/62130401/191060293-183b0b94-180d-42bd-b334-888d7af6dfe4.png)

 $Distance = 10 ^ {((Measured Power – RSSI)/(10 * N))}$

## RSSI to Distance (Meter)
```kotlin

    fun rssiToDistance(rssi: Int,signalStrength:Int): Double {
        return 10.0.pow((1.0 * (signalStrength - rssi)) / (10 * 2)).format(2)
    }

   private fun Double.format(fracDigits: Int): Double {
        val df = DecimalFormat()
        df.maximumFractionDigits = fracDigits
        return df.format(this).toDouble()
    }
```


## BTDevice - Bluetooth Device Model Class

```kotlin
data class BTDevice(
    val name:String = "",
    val id:String,
    var distance: Double
)
```

## BTScanner - Bluetooth Scanner Class

```kotlin
@SuppressLint("MissingPermission")
data class BTScanner(
    private val activity: ComponentActivity,
    private val scanTime : Long,
    private val signalStrength: Int,
    private val viewModel:DevicesViewModel = DevicesViewModel(),

    ) {
    private  var bluetoothAdapter:BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var job : Job? = null

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

```
