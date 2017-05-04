package com.damianogiusti.cleanandroid.ui.home;


import com.damianogiusti.cleanandroid.mvp.View;
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel;

import java.util.List;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public interface MainView extends View {

    void showMessage(String message);

    void showLoading();

    void hideLoading();

    void showList(List<ProvinceViewModel> list);

    void showError(String errorMessage);
}
