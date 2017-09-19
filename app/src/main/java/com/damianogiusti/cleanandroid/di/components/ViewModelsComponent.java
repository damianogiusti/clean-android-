package com.damianogiusti.cleanandroid.di.components;

import com.damianogiusti.cleanandroid.di.PerActivity;
import com.damianogiusti.cleanandroid.di.modules.ViewModelsModule;
import com.damianogiusti.cleanandroid.ui.home.MainActivity;
import com.damianogiusti.cleanandroid.ui.home.MainViewModel;

import dagger.Component;

/**
 * Created by Damiano Giusti on 19/09/17.
 */
@PerActivity
@Component(modules = ViewModelsModule.class, dependencies = {ApplicationComponent.class})
public interface ViewModelsComponent {

    void inject(MainActivity mainActivity);

    MainViewModel mainViewModel();
}
