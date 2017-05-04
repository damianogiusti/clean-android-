package com.damianogiusti.cleanandroid.di.modules;

import android.content.Context;

import com.damianogiusti.cleanandroid.MainApplication;
import com.damianogiusti.domain.cleanandroid.executors.BackgroundThread;
import com.damianogiusti.domain.cleanandroid.executors.MainThreadExecutor;
import com.damianogiusti.domain.cleanandroid.executors.UiThread;
import com.damianogiusti.domain.cleanandroid.executors.WorkerThreadExecutor;
import com.google.gson.Gson;

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
    WorkerThreadExecutor providesLooperThreadExecutor(BackgroundThread backgroundLooperThread) {
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
