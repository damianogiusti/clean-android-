package com.damianogiusti.cleanandroid.ui.home;

import android.os.Bundle;

import com.molo17.cloudnotes.data.datamodel.ProvinceDataModel;
import com.molo17.cloudnotes.domain.interactors.GetProvinceListUseCase;
import com.molo17.cloudnotes.mvp.Presenter;
import com.molo17.cloudnotes.viewmodel.ProvinceViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;

    private GetProvinceListUseCase getProvinceListUseCase;

    @Inject
    public MainPresenter(GetProvinceListUseCase getProvinceListUseCase) {
        this.getProvinceListUseCase = getProvinceListUseCase;
    }

    @Override
    public void create(MainView view, Bundle params) {
        mainView = view;
        mainView.showMessage("Hello from the MainPresenter!");
    }

    @Override
    public void resume() {

        // do this on every resume, for checking the origin of data
        // slow -> REST
        // fast -> Memory Cache

        mainView.showProgress();

        getProvinceListUseCase.use()
                .map(this::mapToViewModel)              // convert the DataModel to ViewModel
                .doOnTerminate(mainView::hideProgress)  // dismiss the dialog on terminate
                .subscribe(this::onProvinceListReceived, this::onError);
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mainView = null;
        // free the resources occupied by the use case
        getProvinceListUseCase.unsubscribe();
    }

    private void onProvinceListReceived(List<ProvinceViewModel> provinceList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ProvinceViewModel provinceViewModel : provinceList) {
            stringBuilder.append(provinceViewModel).append("\n");
        }
        mainView.showMessage(stringBuilder.toString());
    }

    private void onError(Throwable e) {
        mainView.showError("Error fetching the province list! :(");
    }

    /*
     * Why is this model conversion necessary?
     * For example, your business logic models might not be appropriate for showing them to the user directly.
     * Perhaps you need to show a combination of multiple business logic models at once.
     */
    private List<ProvinceViewModel> mapToViewModel(List<ProvinceDataModel> provinceDataModels) {
        List<ProvinceViewModel> provinceViewModels = new ArrayList<>(provinceDataModels.size());
        for (ProvinceDataModel provinceDataModel : provinceDataModels) {
            provinceViewModels.add(new ProvinceViewModel(provinceDataModel.getCodice(), provinceDataModel.getDescrizione()));
        }
        return provinceViewModels;
    }
}
