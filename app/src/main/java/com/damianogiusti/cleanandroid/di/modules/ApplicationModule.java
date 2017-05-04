package com.damianogiusti.cleanandroid.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.molo17.cloudnotes.MainApplication;
import com.molo17.cloudnotes.domain.executors.BackgroundLooperThread;
import com.molo17.cloudnotes.domain.executors.MainThreadExecutor;
import com.molo17.cloudnotes.domain.executors.UiThread;
import com.molo17.cloudnotes.domain.executors.WorkerThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Damiano Giusti on 19/04/17.
 */
@Module
public class ApplicationModule {

    private MainApplication mainApplication;

    public ApplicationModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return mainApplication;
    }

    @Provides
    @Singleton
    WorkerThreadExecutor providesLooperThreadExecutor(BackgroundLooperThread backgroundLooperThread) {
        return backgroundLooperThread;
    }

    @Provides
    @Singleton
    MainThreadExecutor providesMainThreadExecutor(UiThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new Gson();
    }
}
