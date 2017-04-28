package com.childrenofthecorn.pockerplanner.presentation.presenters;

import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.childrenofthecorn.pockerplanner.di.DiManager;
import com.childrenofthecorn.pockerplanner.domain.repository.SprintRepository;
import com.childrenofthecorn.pockerplanner.presentation.state.sprint.SprintInfoState.*;
import com.childrenofthecorn.pockerplanner.presentation.state.sprint.SprintInfoViewState.SprintInfoSuccess;

import javax.inject.Inject;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoPresenter extends BaseMvpPresenter {

    @Inject
    SprintRepository sprintRepository;

    public SprintInfoPresenter() {
        DiManager.getAppComponent().inject(this);
    }

    @Override
    protected void onStateUpdated(PresenterState presenterState) {
        GeneratedSprintInfoPresenterSubscriber.processState(this, presenterState);
    }

    @SubscribeState
    void onRequestSprintInfo(RequestSprintInfo state) {
        sprintRepository.getSprintInfo(this);
    }

    @SubscribeState
    void onReceivedPresenterState(ReceiveSprintInfo state) {
        updateViewState(new SprintInfoSuccess(state.getName(), state.getDate()));
    }
}
