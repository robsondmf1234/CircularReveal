package com.example.circularreveal.anim

import android.os.Build
import android.view.animation.PathInterpolator
import androidx.annotation.RequiresApi

class Easing {
    companion object {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        val EaseinOut = PathInterpolator(0.4f, 0.0f, 0.2f, 1f)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        val EaseOut = PathInterpolator(0.0f, 0.0f, 0.2f, 1f)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        val EaseIn = PathInterpolator(0.4f, 0.0f, 1.0f, 1f)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        val Linear = PathInterpolator(1f, 1f, 1f, 1f)
    }
}