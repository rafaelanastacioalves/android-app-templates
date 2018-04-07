package com.example.rafaelanastacioalves.moby.entities;

public class DeviceSpecs {

    private String device_brand;
    private String device_model;
    private String android_version;


    public DeviceSpecs(String deviceBrand, String deviceModel, String androidVersion) {
        this.device_brand = deviceBrand;
        this.device_model = deviceModel;
        this.android_version = androidVersion;
    }
}
