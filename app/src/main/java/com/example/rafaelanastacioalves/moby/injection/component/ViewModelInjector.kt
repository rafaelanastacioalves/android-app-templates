package com.example.rafaelanastacioalves.moby.injection.component

import com.example.rafaelanastacioalves.moby.injection.module.NetworkModule
import com.example.rafaelanastacioalves.moby.entitydetailing.LiveDataEntityDetailsViewModel
import com.example.rafaelanastacioalves.moby.entitymainlisting.LiveDataMainEntityListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(mainEntityListViewModel: LiveDataMainEntityListViewModel)
    fun inject(entityDetailsViewModel: LiveDataEntityDetailsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}