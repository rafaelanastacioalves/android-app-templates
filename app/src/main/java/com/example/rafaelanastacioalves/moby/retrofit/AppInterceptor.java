package com.example.rafaelanastacioalves.moby.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class AppInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store");

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
