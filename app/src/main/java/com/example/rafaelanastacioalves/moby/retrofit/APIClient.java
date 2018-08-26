package com.example.rafaelanastacioalves.moby.retrofit;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIClient {

    @POST("/trip-packages")
    Call<List<MainEntity>> getTripPackageList();

    @POST("/trip-packages/{tripPackageID}")
    Call<EntityDetails> getTripPackageDetails(@Path("tripPackageID") String id);

}
