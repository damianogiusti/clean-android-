package com.damianogiusti.cleanandroid

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.damianogiusti.cleanandroid.di.components.ApplicationComponent

/**
 * Created by Damiano Giusti on 15/04/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject()
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)

        // Bind views with ButterKnife when the layout has been inflated.
        ButterKnife.bind(this)
    }

    /**
     * Convenience method for injecting Dagger components in the right time during the activity
     * lifecycle.
     */
    @CallSuper
    protected open fun onInject() {
        applicationComponent.inject(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Dagger Application Component
    ///////////////////////////////////////////////////////////////////////////

    protected val applicationComponent: ApplicationComponent
        get() = app.applicationComponent

    ///////////////////////////////////////////////////////////////////////////
    // Utility methods
    ///////////////////////////////////////////////////////////////////////////

    protected fun str(@StringRes stringResId: Int): String {
        return getString(stringResId)
    }

    protected fun str(@StringRes stringResId: Int, vararg args: Any): String {
        return getString(stringResId, *args)
    }

    protected fun extrasFromIntent(intent: Intent?): Bundle? {
        return intent?.extras
    }

    protected val BaseActivity.app: MainApplication
        get() = applicationContext as MainApplication
}
