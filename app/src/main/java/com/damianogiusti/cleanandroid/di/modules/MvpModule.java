package com.damianogiusti.cleanandroid.di.modules;

import com.damianogiusti.cleanandroid.mvp.PresenterProvider;
import com.damianogiusti.cleanandroid.ui.home.MainPresenter;
import com.damianogiusti.domain.cleanandroid.interactors.GetProvinceListUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
@Module
public class MvpModule {

    private PresenterProvider presenterProvider;

    public MvpModule(PresenterProvider presenterProvider) {
        this.presenterProvider = presenterProvider;
    }

    @Provides
    MainPresenter providesMainPresenter(GetProvinceListUseCase getProvinceListUseCase) {
        MainPresenter presenter = (MainPresenter) presenterProvider.getRetainedPresenter();
        if (presenter == null) {
            presenter = new MainPresenter(getProvinceListUseCase);
        }
        return presenter;
    }
}
