package com.example.rafaelanastacioalves.moby.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;

public class DataSourceFactory extends DataSource.Factory<String, MainEntity> {


    private final MutableLiveData<PagedRepoDataSource> sourceLiveData = new MutableLiveData<>();

    public DataSourceFactory() {

    }

    @Override
    public DataSource<String, MainEntity> create() {
        PagedRepoDataSource source = new PagedRepoDataSource();
        sourceLiveData.postValue(source);
        return source;
    }

    public MutableLiveData<PagedRepoDataSource> getDataSource() {
        return sourceLiveData;
    }
}
