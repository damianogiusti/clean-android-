package com.damianogiusti.cleanandroid.mvp;

import com.damianogiusti.cleanandroid.BaseActivity;
import com.damianogiusti.cleanandroid.di.components.DaggerMvpComponent;
import com.damianogiusti.cleanandroid.di.components.MvpComponent;
import com.damianogiusti.cleanandroid.di.modules.MvpModule;

/**
 * Base Activity which allows a Presenter to be retained and be lifecycle aware.
 * This class implements the {@link PresenterProvider} interface, which requires
 * the implementing class to provide the retained instance of the Presenter,
 * and to provide the currently used instance.
 * <p>
 * It uses the {@link PresenterProvider#getPresenter()} method to obtain an
 * instance of the Presenter and it retains it.
 * The retained instance will be obtained by the injected Dagger {@link MvpModule} using
 * the {@link PresenterProvider#getRetainedPresenter()} method, implemented in this
 * class.
 * <p>
 * Created by Damiano Giusti on 20/09/17.
 */
public abstract class MvpActivity extends BaseActivity implements PresenterProvider {

    private MvpComponent mvpComponent;

    @Override
    public final Presenter getRetainedPresenter() {
        return (Presenter) getLastCustomNonConfigurationInstance();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return getPresenter();
    }

    /**
     * Returns the Dagger component used to inject Presenter instances into Activities.
     */
    protected MvpComponent getMvpComponent() {
        if (mvpComponent == null) {
            mvpComponent = DaggerMvpComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .mvpModule(new MvpModule(this))
                    .build();
        }
        return mvpComponent;
    }
}
