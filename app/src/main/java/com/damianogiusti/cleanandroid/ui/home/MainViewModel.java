package com.damianogiusti.cleanandroid.ui.home;

import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel;
import com.damianogiusti.cleanandroid.mvvm.BaseViewModel;
import com.damianogiusti.cleanandroid.uimodel.ProvinceUiModel;
import com.damianogiusti.domain.cleanandroid.interactors.GetProvinceListUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Damiano Giusti on 19/09/17.
 */
public class MainViewModel extends BaseViewModel {

    private Observable<List<ProvinceUiModel>> provinces;
    private Subject<Boolean> loadingSubject;

    @Inject
    public MainViewModel(GetProvinceListUseCase getProvinceListUseCase) {
        provinces = getProvinceListUseCase.use()
                .doOnSubscribe(__ -> loadingSubject.onNext(true))
                .doOnTerminate(() -> loadingSubject.onNext(false))
                .map(this::mapToUiModel)
                .compose(shareReplay(1));

        loadingSubject = ReplaySubject.create(1);
        loadingSubject.onNext(false);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public methods
    ///////////////////////////////////////////////////////////////////////////

    public Observable<List<ProvinceUiModel>> provinces() {
        return provinces;
    }

    public Observable<Boolean> isLoading() {
        return loadingSubject;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    /*
     * Why is this model conversion necessary?
     * For example, your business logic models might not be appropriate for showing them to the user directly.
     * Perhaps you need to show a combination of multiple business logic models at once.
     */
    private List<ProvinceUiModel> mapToUiModel(List<ProvinceDataModel> provinceDataModels) {
        List<ProvinceUiModel> provinceUiModels = new ArrayList<>(provinceDataModels.size());
        for (ProvinceDataModel provinceDataModel : provinceDataModels) {
            provinceUiModels.add(new ProvinceUiModel(provinceDataModel.getSigla(), provinceDataModel.getDescrizione()));
        }
        return provinceUiModels;
    }
}
