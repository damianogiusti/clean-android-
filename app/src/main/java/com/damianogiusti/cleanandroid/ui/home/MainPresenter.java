package com.damianogiusti.cleanandroid.ui.home;

import android.util.Log;

import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel;
import com.damianogiusti.cleanandroid.mvp.Presenter;
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel;
import com.damianogiusti.domain.cleanandroid.interactors.GetProvinceListUseCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class MainPresenter implements Presenter<MainView> {

    private static final String TAG = "MainPresenter";

    private MainView mainView;

    private Observable<List<ProvinceViewModel>> observableProvinces;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenter(GetProvinceListUseCase getProvinceListUseCase) {
        observableProvinces = getProvinceListUseCase.use()
                .map(this::mapToViewModel)      // convert the DataModel to ViewModel
                .share()
                .replay(1)
                .autoConnect();

        Log.d(TAG, "MainPresenter: created " + this);
    }

    @Override
    public void attachView(MainView view) {
        mainView = view;

        Log.d(TAG, "attachView into presenter " + this);

        observableProvinces
                .doOnSubscribe(compositeDisposable::add)
                .doOnTerminate(mainView::hideLoading)   // dismiss the dialog on terminate
                .subscribe(this::onProvinceListReceived, this::onError);
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();

        Log.d(TAG, "detachView from presenter " + this);
    }

    private void onProvinceListReceived(List<ProvinceViewModel> provinceList) {
        mainView.showList(provinceList);
    }

    private void onError(Throwable e) {
        mainView.showError("Error fetching the province list! :(");
    }

    /*
     * Why is this model conversion necessary?
     * For example, your business logic models might not be appropriate for showing them to the user directly.
     * Perhaps you need to show a combination of multiple business logic models at once.
     */
    private List<ProvinceViewModel> mapToViewModel(Collection<ProvinceDataModel> provinceDataModels) {
        List<ProvinceViewModel> provinceViewModels = new ArrayList<>(provinceDataModels.size());
        for (ProvinceDataModel provinceDataModel : provinceDataModels) {
            provinceViewModels.add(new ProvinceViewModel(provinceDataModel.getSigla(), provinceDataModel.getDescrizione()));
        }
        return provinceViewModels;
    }
}
