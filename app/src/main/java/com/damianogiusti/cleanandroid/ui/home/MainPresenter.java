package com.damianogiusti.cleanandroid.ui.home;

import android.os.Bundle;

import com.damianogiusti.cleanandroid.mvp.Presenter;

import javax.inject.Inject;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;

    @Inject
    public MainPresenter() {}

    @Override
    public void create(MainView view, Bundle params) {
        mainView = view;
        mainView.showMessage("Hello from the MainPresenter!");
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mainView = null;
    }
}
