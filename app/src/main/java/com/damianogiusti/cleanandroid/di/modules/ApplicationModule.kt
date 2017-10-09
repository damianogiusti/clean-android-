package com.damianogiusti.cleanandroid.di.modules

import android.content.Context

import com.damianogiusti.cleanandroid.MainApplication
import com.damianogiusti.domain.cleanandroid.executors.BackgroundThread
import com.damianogiusti.domain.cleanandroid.executors.MainThreadExecutor
import com.damianogiusti.domain.cleanandroid.executors.UiThread
import com.damianogiusti.domain.cleanandroid.executors.WorkerThreadExecutor
import com.google.gson.Gson

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by Damiano Giusti on 19/04/17.
 */
@Module
class ApplicationModule(private val mainApplication: MainApplication) {

    @Provides
    @Singleton
    internal fun providesApplicationContext(): Context {
        return mainApplication
    }

    @Provides
    @Singleton
    internal fun providesLooperThreadExecutor(backgroundLooperThread: BackgroundThread): WorkerThreadExecutor {
        return backgroundLooperThread
    }

    @Provides
    @Singleton
    internal fun providesMainThreadExecutor(uiThread: UiThread): MainThreadExecutor {
        return uiThread
    }

    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return Gson()
    }
}
