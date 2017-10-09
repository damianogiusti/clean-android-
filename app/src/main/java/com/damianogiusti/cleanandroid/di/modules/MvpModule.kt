package com.damianogiusti.cleanandroid.di.modules

import com.damianogiusti.cleanandroid.mvp.PresenterProvider
import com.damianogiusti.cleanandroid.ui.home.MainPresenter
import com.damianogiusti.domain.cleanandroid.interactors.GetProvinceListUseCase

import dagger.Module
import dagger.Provides

/**
 * Created by Damiano Giusti on 03/05/17.
 */
@Module
class MvpModule(private val presenterProvider: PresenterProvider) {

    @Provides
    internal fun providesMainPresenter(getProvinceListUseCase: GetProvinceListUseCase): MainPresenter {
        var presenter = presenterProvider.getRetainedPresenter() as MainPresenter?
        if (presenter == null) {
            presenter = MainPresenter(getProvinceListUseCase)
        }
        return presenter
    }
}
