package com.damianogiusti.cleanandroid.di.components;

import com.damianogiusti.cleanandroid.di.PerActivity;
import com.damianogiusti.cleanandroid.di.modules.MvpModule;
import com.damianogiusti.cleanandroid.ui.home.MainActivity;
import com.damianogiusti.cleanandroid.ui.home.MainPresenter;

import dagger.Component;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MvpModule.class)
public interface MvpComponent {

    void inject(MainActivity activity);

    MainPresenter mainPresenter();
}
