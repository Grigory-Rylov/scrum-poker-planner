package com.childrenofthecorn.pockerplanner.presentation.presenters;

import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.childrenofthecorn.pockerplanner.di.DiManager;
import com.childrenofthecorn.pockerplanner.domain.repository.AuthRepository;
import com.childrenofthecorn.pockerplanner.presentation.state.auth.JoinToSprintState.*;
import com.childrenofthecorn.pockerplanner.presentation.state.auth.JointToSprintViewState.*;

import javax.inject.Inject;

/**
 * Created by grishberg on 26.02.17.
 */

public class JoinToSprintPresenter extends BaseMvpPresenter {
    @Inject
    AuthRepository authRepository;

    public JoinToSprintPresenter() {
        DiManager.getAppComponent().inject(this);
    }

    @Override
    protected void onStateUpdated(PresenterState presenterState) {
        GeneratedJoinToSprintPresenterSubscriber.processState(this, presenterState);
    }

    @SubscribeState
    void registerMember(JointToSprintRequest presenterState) {
        updateViewState(new JoinToSprintModelStateProgress());

        authRepository.registerMemberToSprint(presenterState.getName(),
                presenterState.getSprintToken(),
                this
        );
    }

    @SubscribeState
    void onJointToSprintSuccess(JointToSprintSuccessResponse state) {
        updateViewState(new JoinToSprintModelStateSuccess());
    }

    @SubscribeState
    void onJointToSprintFail(JointToSprintFailResponse state) {
        updateViewState(new JoinToSprintModelStateError(state.getException()));
    }
}
