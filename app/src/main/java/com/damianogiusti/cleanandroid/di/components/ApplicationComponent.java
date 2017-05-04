package com.damianogiusti.cleanandroid.di.components;

import android.content.Context;

import com.damianogiusti.cleanandroid.BaseActivity;
import com.damianogiusti.cleanandroid.di.modules.ApplicationModule;
import com.damianogiusti.domain.cleanandroid.executors.MainThreadExecutor;
import com.damianogiusti.domain.cleanandroid.executors.WorkerThreadExecutor;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Damiano Giusti on 15/04/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    Context context();

    WorkerThreadExecutor looperThreadExecutor();

    MainThreadExecutor mainThreadExecutor();

    Gson gson();
}
