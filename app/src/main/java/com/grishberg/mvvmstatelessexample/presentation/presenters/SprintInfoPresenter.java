package com.grishberg.mvvmstatelessexample.presentation.presenters;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvvmstatelessexample.di.DiManager;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoState;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoPresenter extends BaseMvpPresenter<SprintInfoState> {


    public SprintInfoPresenter() {
        DiManager.getAppComponent().inject(this);
    }

    @Override
    protected void onStateUpdated(MvpState presenterState) {
        //Not implemented yet
    }
}
