package com.damianogiusti.cleanandroid.mvp;

import android.os.Bundle;

/**
 * Created by Damiano Giusti on 15/04/17.
 */
public interface Presenter<T extends View> {

    void create(T view, Bundle params);

    void resume();

    void pause();

    void destroy();
}
