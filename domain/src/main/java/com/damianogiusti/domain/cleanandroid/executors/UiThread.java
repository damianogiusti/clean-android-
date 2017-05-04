package com.damianogiusti.domain.cleanandroid.executors;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Damiano Giusti on 28/04/17.
 */
public class UiThread implements MainThreadExecutor {

    @Inject
    public UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
