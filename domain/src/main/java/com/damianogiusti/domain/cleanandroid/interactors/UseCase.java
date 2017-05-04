package com.damianogiusti.domain.cleanandroid.interactors;

import com.damianogiusti.domain.cleanandroid.executors.MainThreadExecutor;
import com.damianogiusti.domain.cleanandroid.executors.WorkerThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Damiano Giusti on 28/04/17.
 */
public abstract class UseCase<T> {

    private WorkerThreadExecutor workerThreadExecutor;
    private MainThreadExecutor mainThreadExecutor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected UseCase(WorkerThreadExecutor workerThreadExecutor, MainThreadExecutor mainThreadExecutor) {
        this.workerThreadExecutor = workerThreadExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public Observable<T> use() {
        return getUseCaseObservable()
                .subscribeOn(workerThreadExecutor.getScheduler())
                .observeOn(mainThreadExecutor.getScheduler())
                .doOnSubscribe(compositeDisposable::add);
    }

    public abstract Observable<T> getUseCaseObservable();

    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
