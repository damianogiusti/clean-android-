package com.damianogiusti.cleanandroid.data.datasources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public abstract class BaseInMemoryCacheDataSource<T> {

    private static class MemoryCacheExpired extends Throwable {}

    private List<T> dataset;
    private long expirationTime;

    private long ttl = 1;
    private TimeUnit ttlTimeUnit = TimeUnit.DAYS;

    public void setDataset(List<T> dataset) {
        this.dataset = dataset;
        this.expirationTime = System.currentTimeMillis() + ttlTimeUnit.toMillis(ttl);
    }

    public List<T> getDataset() {
        if (System.currentTimeMillis() > expirationTime) {
            // cache expired, clear dataset
            if (dataset != null) {
                dataset.clear();
                dataset = null;
            }
        }
        return dataset;
    }

    public Observable<List<T>> getDatasetObservable() {
        return Observable
                .defer(() -> {
                    List<T> dataset = getDataset();
                    if (dataset == null) {
                        return Observable.error(new MemoryCacheExpired());
                    }
                    return Observable.just(dataset);
                });
    }

    /**
     * Sets the cache TTL
     */
    public void setCacheTtl(long ttl, TimeUnit timeUnit) {
        this.ttl = ttl;
        this.ttlTimeUnit = timeUnit;
    }
}
