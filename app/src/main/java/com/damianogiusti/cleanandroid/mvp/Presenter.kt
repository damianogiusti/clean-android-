package com.damianogiusti.cleanandroid.mvp

/**
 * Created by Damiano Giusti on 15/04/17.
 */
interface Presenter<in T : View> {

    fun attachView(view: T)

    fun detachView()
}
