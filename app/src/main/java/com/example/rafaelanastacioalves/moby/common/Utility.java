package com.example.rafaelanastacioalves.moby.common;

import android.os.Build;


public class Utility {

    //TODO Implement
    public static String getDeviceBrand(){
        return Build.MANUFACTURER;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }
}
