package com.yonigofman.bluetoothscannerapp.ui.components

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.*
import com.airbnb.lottie.compose.*
import com.yonigofman.bluetoothscannerapp.R
import kotlin.concurrent.timer

@Composable
fun LottieScanAnimation(isPlaying:Boolean) {



    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scan_animation3))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever,
        speed = 1f,
    )

    LottieAnimation(composition = composition, progress = { progress } )



}