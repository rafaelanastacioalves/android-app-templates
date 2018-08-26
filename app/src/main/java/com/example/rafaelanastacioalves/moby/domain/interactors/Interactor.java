package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.MutableLiveData;

public interface Interactor {

    <T extends MutableLiveData> T execute();
}
