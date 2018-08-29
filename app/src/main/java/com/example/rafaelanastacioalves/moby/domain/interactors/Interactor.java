package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.MutableLiveData;

public interface Interactor <R extends Interactor.RequestValues> {

    <T extends MutableLiveData> T execute(R requestValue);

    public interface RequestValues {
    }
}
