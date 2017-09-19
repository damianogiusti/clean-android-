package com.damianogiusti.cleanandroid.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.damianogiusti.cleanandroid.ui.home.MainViewModel;
import com.damianogiusti.domain.cleanandroid.interactors.GetProvinceListUseCase;

import javax.inject.Inject;

/**
 * Created by Damiano Giusti on 19/09/17.
 */
@SuppressWarnings("unchecked")
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private GetProvinceListUseCase getProvinceListUseCase;

    @Inject
    public MainViewModelFactory(GetProvinceListUseCase getProvinceListUseCase) {
        this.getProvinceListUseCase = getProvinceListUseCase;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(getProvinceListUseCase);
        }
        throw new IllegalStateException();
    }
}
