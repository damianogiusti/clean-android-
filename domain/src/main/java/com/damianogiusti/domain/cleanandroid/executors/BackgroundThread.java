package com.damianogiusti.domain.cleanandroid.executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Damiano Giusti on 28/04/17.
 */
@Singleton
public class BackgroundThread implements WorkerThreadExecutor {

    @Inject
    public BackgroundThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
