package com.damianogiusti.domain.cleanandroid.executors;

import io.reactivex.Scheduler;

/**
 * Created by Damiano Giusti on 28/04/17.
 */
public interface RxExecutor {
    Scheduler getScheduler();
}
