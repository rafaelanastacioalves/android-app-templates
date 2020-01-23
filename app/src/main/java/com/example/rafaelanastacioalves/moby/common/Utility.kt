package com.example.rafaelanastacioalves.moby.common

import android.os.Build


object Utility {

    //TODO Implement
    val deviceBrand: String
        get() = Build.MANUFACTURER

    val deviceModel: String
        get() = Build.MODEL

    val androidVersion: String
        get() = Build.VERSION.RELEASE
}
