package com.damianogiusti.cleanandroid.mvp;

/**
 * Created by Damiano Giusti on 15/04/17.
 */
public interface Presenter<T extends View> {

    void attachView(T view);

    void detachView();
}
