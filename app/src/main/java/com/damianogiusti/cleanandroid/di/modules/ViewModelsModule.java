package com.damianogiusti.cleanandroid.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import com.damianogiusti.cleanandroid.BaseActivity;
import com.damianogiusti.cleanandroid.mvvm.MainViewModelFactory;
import com.damianogiusti.cleanandroid.ui.home.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Damiano Giusti on 19/09/17.
 */
@Module
public class ViewModelsModule {

    private BaseActivity activity;

    public ViewModelsModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public MainViewModel providesMainViewModel(MainViewModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }
}
