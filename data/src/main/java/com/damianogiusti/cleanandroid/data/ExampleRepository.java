package com.damianogiusti.cleanandroid.data;

import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel;
import com.damianogiusti.cleanandroid.data.datasources.ProvinceMemoryCacheDataSource;
import com.damianogiusti.cleanandroid.data.datasources.ProvinceRestDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class ExampleRepository extends BaseRepository {

    private ProvinceRestDataSource provinceRestDataSource;
    private ProvinceMemoryCacheDataSource provinceMemoryCacheDataSource;

    @Inject
    public ExampleRepository(ProvinceRestDataSource provinceRestDataSource, ProvinceMemoryCacheDataSource provinceMemoryCacheDataSource) {
        this.provinceRestDataSource = provinceRestDataSource;
        this.provinceMemoryCacheDataSource = provinceMemoryCacheDataSource;
    }

    public Observable<List<ProvinceDataModel>> getProvinceList() {
        // first get elements from in-memory cache
        // if got error (cache miss), get from REST service and save in in-memory cache
        return provinceMemoryCacheDataSource.getDatasetObservable()
                .onErrorResumeNext(provinceRestDataSource.getProvinceList().doOnNext(provinceMemoryCacheDataSource::setDataset));
    }
}
