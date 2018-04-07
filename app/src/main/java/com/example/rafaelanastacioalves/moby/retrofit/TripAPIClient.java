package com.example.rafaelanastacioalves.moby.retrofit;

import com.example.rafaelanastacioalves.moby.entities.DeviceSpecs;
import com.example.rafaelanastacioalves.moby.entities.TripPackage;
import com.example.rafaelanastacioalves.moby.entities.TripPackageDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TripAPIClient {

    @POST("/trip-packages")
    Call<List<TripPackage>> getTripPackageList(@Body DeviceSpecs deviceSpecs);

    @POST("/trip-packages/{tripPackageID}")
    Call<TripPackageDetails> getTripPackageDetails(@Body DeviceSpecs deviceSpecs, @Path("tripPackageID") String id);

}
