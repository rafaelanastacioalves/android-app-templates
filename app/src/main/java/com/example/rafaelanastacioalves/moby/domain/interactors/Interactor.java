package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public interface Interactor <R extends Interactor.RequestValues> {

    <T extends LiveData> T execute(R requestValue);

    public interface RequestValues {
    }
}
