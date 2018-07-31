package com.example.rafaelanastacioalves.moby.repository;

import com.example.rafaelanastacioalves.moby.vo.MainEntity;
import com.example.rafaelanastacioalves.moby.vo.EntityDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIClient {

    @POST("/trip-packages")
    Observable<List<MainEntity>> getTripPackageList();

    @POST("/trip-packages-additional")
    Observable<List<MainEntity>> getTripPackageListAdditional();

    @POST("/trip-packages/{tripPackageID}")
    Observable<EntityDetails> getTripPackageDetails(@Path("tripPackageID") String id);

}
