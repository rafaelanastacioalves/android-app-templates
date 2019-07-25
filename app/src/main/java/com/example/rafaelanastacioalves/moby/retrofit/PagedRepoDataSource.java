package com.example.rafaelanastacioalves.moby.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.example.rafaelanastacioalves.moby.retrofit.NetworkBoundSource.ERRO_DE_NETWORK;


public class PagedRepoDataSource extends PageKeyedDataSource<String, MainEntity> {

    private final MutableLiveData<Boolean> loadStatus = new MutableLiveData<>();

    public PagedRepoDataSource() {
        loadStatus.postValue(Boolean.TRUE);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, MainEntity> callback) {
        loadStatus.postValue(Boolean.TRUE);

        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        Call<List<MainEntity>> call = apiClient.getTripPackageList(String.valueOf(1));
        call.enqueue(new Callback<List<MainEntity>>() {
            @Override
            public void onResponse(Call<List<MainEntity>> call, Response<List<MainEntity>> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200){
                        Timber.d("API response: Successful");
                        callback.onResult(response.body(),"", String.valueOf(1+1));
                    }else {
                        Timber.w("API response: NOT Successful");
                    }

                }
            }

            @Override
            public void onFailure(Call<List<MainEntity>> call, Throwable t) {
                //Aqui pode ter uma mensagem personalizada... Não deveria ter mensagem
                //De throwable
                Timber.e("Erro de network: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<Boolean> getLiveLoadStatus() {
        return loadStatus;
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, MainEntity> callback) {
        // no need to go back here
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, MainEntity> callback) {
        loadStatus.postValue(Boolean.TRUE);
        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        Call<List<MainEntity>> call = apiClient.getTripPackageList(String.valueOf(params.key));
        call.enqueue(new Callback<List<MainEntity>>() {
            @Override
            public void onResponse(Call<List<MainEntity>> call, Response<List<MainEntity>> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200){
                        loadStatus.postValue(Boolean.FALSE);
                        Timber.d("API response: Successful");
                        callback.onResult(
                                response.body(),
                                String.valueOf(Integer.valueOf(params.key) + 1) );
                    }else {
                        Timber.w("API response: NOT Successful");
                    }

                }
            }

            @Override
            public void onFailure(Call<List<MainEntity>> call, Throwable t) {
                //Aqui pode ter uma mensagem personalizada... Não deveria ter mensagem
                //De throwable
                Timber.e("Erro de network: " + t.getMessage());
            }
        });

    }



}







