package com.damianogiusti.cleanandroid.data.datasources;


import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel;

import javax.inject.Inject;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class ProvinceMemoryCacheDataSource extends BaseInMemoryCacheDataSource<ProvinceDataModel> {

    @Inject
    public ProvinceMemoryCacheDataSource() {}
}
