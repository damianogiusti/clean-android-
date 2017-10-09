package com.damianogiusti.cleanandroid.ui.home

import android.util.Log

import com.damianogiusti.cleanandroid.data.datamodel.ProvinceDataModel
import com.damianogiusti.cleanandroid.mvp.Presenter
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel
import com.damianogiusti.domain.cleanandroid.interactors.GetProvinceListUseCase

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Damiano Giusti on 03/05/17.
 */
class MainPresenter(getProvinceListUseCase: GetProvinceListUseCase) : Presenter<MainView> {

    private var mainView: MainView? = null

    private val observableProvinces: Observable<List<ProvinceViewModel>>

    private val compositeDisposable = CompositeDisposable()

    init {
        observableProvinces = getProvinceListUseCase.use()
                .map { mapToViewModel(it) }      // convert the DataModel to ViewModel
                .share()
                .replay(1)
                .autoConnect()

        Log.d(TAG, "MainPresenter: created " + this)
    }

    override fun attachView(view: MainView) {
        mainView = view

        Log.d(TAG, "attachView into presenter " + this)

        observableProvinces
                .doOnSubscribe { compositeDisposable.add(it) }
                .doOnTerminate { mainView?.hideLoading() }   // dismiss the dialog on terminate
                .subscribe ({ onProvinceListReceived(it) }, { this.onError(it) })
    }

    override fun detachView() {
        compositeDisposable.clear()

        Log.d(TAG, "detachView from presenter " + this)
    }

    private fun onProvinceListReceived(provinceList: List<ProvinceViewModel>) {
        mainView?.showList(provinceList)
    }

    private fun onError(e: Throwable) {
        mainView?.showError("Error fetching the province list! :(")
    }

    /*
     * Why is this model conversion necessary?
     * For example, your business logic models might not be appropriate for showing them to the user directly.
     * Perhaps you need to show a combination of multiple business logic models at once.
     */
    private fun mapToViewModel(provinceDataModels: Collection<ProvinceDataModel>): List<ProvinceViewModel> {
        val provinceViewModels = ArrayList<ProvinceViewModel>(provinceDataModels.size)
        provinceDataModels.mapTo(provinceViewModels) { ProvinceViewModel(it.sigla, it.descrizione) }
        return provinceViewModels
    }

    companion object {

        private val TAG = "MainPresenter"
    }
}
