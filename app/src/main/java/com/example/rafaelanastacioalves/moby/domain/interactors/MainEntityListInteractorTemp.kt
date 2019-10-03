package com.example.rafaelanastacioalves.moby.domain.interactors

import com.example.rafaelanastacioalves.moby.retrofit.AppRepository

class MainEntityListInteractorTemp(val appRepository: AppRepository): Interactor<MainEntityListInteractorTemp.RequestValues> {
    override fun <T> execute(resquestValue: RequestValues): T  {

    }


    class RequestValues: Interactor_temp.RequestValues {

    }

}