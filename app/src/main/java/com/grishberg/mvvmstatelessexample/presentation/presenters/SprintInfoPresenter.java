package com.grishberg.mvvmstatelessexample.presentation.presenters;

import android.support.annotation.NonNull;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvvmstatelessexample.di.DiManager;
import com.grishberg.mvvmstatelessexample.domain.repository.SprintRepository;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoState;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoState.*;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoViewState;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoViewState.SprintInfoSuccess;

import javax.inject.Inject;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoPresenter extends BaseMvpPresenter<SprintInfoViewState> {

    @Inject
    SprintRepository sprintRepository;

    public SprintInfoPresenter() {
        DiManager.getAppComponent().inject(this);
    }

    @Override
    protected void onStateUpdated(MvpState presenterState) {
        if (presenterState instanceof RequestSprintInfo) {
            sprintRepository.getSprintInfo(this);
            return;
        }

        if (presenterState instanceof ReceiveSprintInfo) {
            onReceivedPresenterState((ReceiveSprintInfo) presenterState);
            return;
        }
    }

    private void onReceivedPresenterState(@NonNull ReceiveSprintInfo state) {
        updateViewState(new SprintInfoSuccess(state.getName(), state.getDate()));
    }
}
