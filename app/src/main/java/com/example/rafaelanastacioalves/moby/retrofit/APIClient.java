package com.example.rafaelanastacioalves.moby.retrofit;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.entities.EntityDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIClient {

    @POST("/trip-packages")
    Observable<List<MainEntity>> getTripPackageList();

    @POST("/trip-packages/{tripPackageID}")
    Call<EntityDetails> getTripPackageDetails(@Path("tripPackageID") String id);

}
