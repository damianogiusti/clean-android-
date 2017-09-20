package com.damianogiusti.cleanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.damianogiusti.cleanandroid.di.components.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by Damiano Giusti on 15/04/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInject();
    }

    /**
     * Convenience method for injecting Dagger components in the right time during the activity
     * lifecycle.
     */
    @CallSuper
    protected void onInject() {
        getApplicationComponent().inject(this);
    }

    @Override
    public MainApplication getApplicationContext() {
        return (MainApplication) super.getApplicationContext();
    }

    /**
     * Convenience method for binding the annotated views of the given activity.
     * Override it if you want some custom bindings.
     *
     * @param activity Must extend from {@link BaseActivity}
     */
    @CallSuper
    protected <T extends BaseActivity> void bindViews(T activity) {
        ButterKnife.bind(activity);
    }

    // Dagger components
    ///////////////////////////////////////////////////////////////////////////
    // Dagger Application Component
    ///////////////////////////////////////////////////////////////////////////

    protected ApplicationComponent getApplicationComponent() {
        return getApplicationContext().getApplicationComponent();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Utility methods
    ///////////////////////////////////////////////////////////////////////////

    protected String str(@StringRes int stringResId) {
        return getString(stringResId);
    }

    protected String str(@StringRes int stringResId, Object... args) {
        return getString(stringResId, args);
    }

    protected Bundle extrasFromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getExtras();
    }
}
