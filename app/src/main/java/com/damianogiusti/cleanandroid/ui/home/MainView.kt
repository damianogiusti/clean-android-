package com.damianogiusti.cleanandroid.ui.home


import com.damianogiusti.cleanandroid.mvp.View
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel

/**
 * Created by Damiano Giusti on 03/05/17.
 */
interface MainView : View {

    fun showMessage(message: String)

    fun showLoading()

    fun hideLoading()

    fun showList(list: List<ProvinceViewModel>)

    fun showError(errorMessage: String)
}
