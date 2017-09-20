package com.damianogiusti.cleanandroid.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Interface which constrains a class to provide a Presenter and its
 * retained instance.
 * Implemented by the {@link MvpActivity}, which will retain it's Presenter
 * through the {@link AppCompatActivity#onRetainCustomNonConfigurationInstance()}.
 *
 * Created by Damiano Giusti on 20/09/17.
 */
public interface PresenterProvider {

    /**
     * Returns the Presenter instance currently used in the implementing class.
     */
    @NonNull Presenter getPresenter();

    /**
     * Returns the retained instance of the Presenter.
     */
    @Nullable Presenter getRetainedPresenter();
}
