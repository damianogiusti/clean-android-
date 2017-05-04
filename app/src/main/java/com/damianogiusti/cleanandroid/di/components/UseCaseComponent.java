package com.damianogiusti.cleanandroid.di.components;

import com.damianogiusti.cleanandroid.di.PerActivity;
import com.damianogiusti.cleanandroid.di.modules.UseCaseModule;
import com.damianogiusti.cleanandroid.ui.home.MainActivity;

import dagger.Component;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = UseCaseModule.class)
public interface UseCaseComponent {
    void inject(MainActivity activity);
}
