package com.damianogiusti.cleanandroid

import android.app.Application

import com.damianogiusti.cleanandroid.di.components.ApplicationComponent
import com.damianogiusti.cleanandroid.di.components.DaggerApplicationComponent
import com.damianogiusti.cleanandroid.di.modules.ApplicationModule


/**
 * Created by Damiano Giusti on 19/04/17.
 */
class MainApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}
