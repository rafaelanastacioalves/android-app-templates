package com.example.rafaelanastacioalves.moby.Dagger;

import com.example.rafaelanastacioalves.moby.application.MainApplication;
import com.example.rafaelanastacioalves.moby.entitymainlisting.LiveDataMainEntityListViewModel;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainActivity;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {AndroidInjectionModule.class, MyApplicationModule.class})
public interface MyApplicationComponent extends AndroidInjector<MainApplication>{
}
