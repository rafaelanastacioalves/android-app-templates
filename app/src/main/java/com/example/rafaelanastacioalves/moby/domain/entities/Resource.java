package com.example.rafaelanastacioalves.moby.domain.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Resource<T> {
    @NonNull  public Status status;
    @Nullable public T data;
    @Nullable public String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data){
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(Status status, @Nullable T data, String msg){
        return new Resource<>(status, data, msg );
    }

    public static <T> Resource<T> loading(@Nullable T data){
        return new Resource<>(Status.LOADING, data, null);
    }

    public enum Status { SUCCESS, INTERNAL_SERVER_ERROR ,GENERIC_ERROR, LOADING}
}
