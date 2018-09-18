package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

public class MainEntityListInteractorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    AppRepository appRepository;



    @Test
    public void shouldReturnLiveDataAfterExecute(){
        MainEntityListInteractor interactor = new MainEntityListInteractor(appRepository);
        MainEntityListInteractor.RequestValues resquestValue = null;

        Object returnedObject = interactor.execute(resquestValue);
        Assert.assertThat(returnedObject.getClass(), equals( ));
    }


}
