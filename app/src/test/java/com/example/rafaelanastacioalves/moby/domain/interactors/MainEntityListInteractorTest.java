package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.reactivex.Single.just;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;

public class MainEntityListInteractorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    AppRepository appRepository;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    @Mock
    private Call<List<MainEntity>> mockedCall;

    @Test
    public void shouldReturnLiveDataAfterExecute(){
        MainEntityListInteractor interactor = new MainEntityListInteractor(appRepository);
        MainEntityListInteractor.RequestValues resquestValue = null;

        when(appRepository.getMainEntityList()).thenReturn(
                new LiveData<Resource<List<MainEntity>>>() {

                }
        );

        Object returnedObject = interactor.execute(resquestValue);
        Assert.assertThat(returnedObject, instanceOf(LiveData.class));
    }


}
