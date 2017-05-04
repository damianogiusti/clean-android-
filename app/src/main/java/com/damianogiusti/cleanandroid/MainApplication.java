package com.damianogiusti.cleanandroid;

import android.app.Application;

import com.damianogiusti.cleanandroid.di.components.ApplicationComponent;
import com.damianogiusti.cleanandroid.di.modules.ApplicationModule;


/**
 * Created by Damiano Giusti on 19/04/17.
 */
public class MainApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createAppComponent();
    }

    private void createAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
