package com.damianogiusti.cleanandroid.mvp

import android.support.v7.app.AppCompatActivity

/**
 * Interface which constrains a class to provide a Presenter and its
 * retained instance.
 * Implemented by the [MvpActivity], which will retain it's Presenter
 * through the [AppCompatActivity.onRetainCustomNonConfigurationInstance].
 *
 * Created by Damiano Giusti on 20/09/17.
 */
interface PresenterProvider {

    /**
     * Returns the Presenter instance currently used in the implementing class.
     */
    fun getPresenter(): Presenter<*>

    /**
     * Returns the retained instance of the Presenter.
     */
    fun getRetainedPresenter(): Presenter<*>?
}
