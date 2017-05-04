package com.damianogiusti.cleanandroid.ui.home;

import com.molo17.cloudnotes.mvp.View;
import com.molo17.cloudnotes.viewmodel.ProvinceViewModel;

import java.util.List;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public interface MainView extends View {

    void showMessage(String message);

    void showProvinces(List<ProvinceViewModel> provincesList);

    void showProgress();

    void hideProgress();

    void showError(String errorMessage);
}
