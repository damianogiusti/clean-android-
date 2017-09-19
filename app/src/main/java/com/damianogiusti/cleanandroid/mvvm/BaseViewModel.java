package com.damianogiusti.cleanandroid.mvvm;

import android.arch.lifecycle.ViewModel;

import io.reactivex.ObservableTransformer;

/**
 * Created by Damiano Giusti on 19/09/17.
 */
public abstract class BaseViewModel extends ViewModel {

    protected <T> ObservableTransformer<T, T> shareReplay(int count) {
        return upstream -> upstream
                .share()
                .replay(count)
                .autoConnect();
    }
}
