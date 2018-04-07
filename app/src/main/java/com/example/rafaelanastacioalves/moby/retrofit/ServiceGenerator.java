package com.example.rafaelanastacioalves.moby.retrofit;

import com.example.rafaelanastacioalves.moby.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit retrofit = builder.client(httpClient
                .addInterceptor(interceptor)
                .build()).build();
        return retrofit.create(serviceClass);
    }

}
