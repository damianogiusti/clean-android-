package com.damianogiusti.cleanandroid.mvp

import com.damianogiusti.cleanandroid.BaseActivity
import com.damianogiusti.cleanandroid.di.components.DaggerMvpComponent
import com.damianogiusti.cleanandroid.di.components.MvpComponent
import com.damianogiusti.cleanandroid.di.modules.MvpModule

/**
 * Base Activity which allows a Presenter to be retained and be lifecycle aware.
 * This class implements the [PresenterProvider] interface, which requires
 * the implementing class to provide the retained instance of the Presenter,
 * and to provide the currently used instance.
 *
 *
 * It uses the [PresenterProvider.getPresenter] method to obtain an
 * instance of the Presenter and it retains it.
 * The retained instance will be obtained by the injected Dagger [MvpModule] using
 * the [PresenterProvider.getRetainedPresenter] method, implemented in this
 * class.
 *
 *
 * Created by Damiano Giusti on 20/09/17.
 */
abstract class MvpActivity : BaseActivity(), PresenterProvider {

    /**
     * The Dagger component used to inject Presenter instances into Activities.
     */
    val mvpComponent: MvpComponent by lazy {
        DaggerMvpComponent.builder()
                .applicationComponent(applicationComponent)
                .mvpModule(MvpModule(this))
                .build()
    }

    override fun getRetainedPresenter(): Presenter<*>? =
            lastNonConfigurationInstance as? Presenter<*>

    override fun onRetainCustomNonConfigurationInstance(): Any = getPresenter()
}
