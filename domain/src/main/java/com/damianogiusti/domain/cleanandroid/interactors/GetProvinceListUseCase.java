package com.damianogiusti.domain.cleanandroid.interactors;

import com.damianogiusti.cleanandroid.data.ExampleRepository;
import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel;
import com.damianogiusti.domain.cleanandroid.executors.MainThreadExecutor;
import com.damianogiusti.domain.cleanandroid.executors.WorkerThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class GetProvinceListUseCase extends UseCase<List<ProvinceDataModel>> {

    private ExampleRepository exampleRepository;

    @Inject
    protected GetProvinceListUseCase(ExampleRepository exampleRepository,
                                     WorkerThreadExecutor workerThreadExecutor, MainThreadExecutor mainThreadExecutor) {
        super(workerThreadExecutor, mainThreadExecutor);
        this.exampleRepository = exampleRepository;
    }

    @Override
    public Observable<List<ProvinceDataModel>> getUseCaseObservable() {
        return exampleRepository.getProvinceList();
    }
}
