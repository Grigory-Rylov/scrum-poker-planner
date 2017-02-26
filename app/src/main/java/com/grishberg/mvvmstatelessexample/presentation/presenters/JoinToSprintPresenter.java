package com.grishberg.mvvmstatelessexample.presentation.presenters;

import android.support.annotation.NonNull;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvvmstatelessexample.di.DiManager;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException;
import com.grishberg.mvvmstatelessexample.domain.repository.AuthRepository;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JoinToSprintState.*;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState.*;

import javax.inject.Inject;

/**
 * Created by grishberg on 26.02.17.
 */

public class JoinToSprintPresenter extends BaseMvpPresenter<JointToSprintViewState> {
    @Inject
    AuthRepository authRepository;

    public JoinToSprintPresenter() {
        DiManager.getAppComponent().inject(this);
    }

    @Override
    protected void onStateUpdated(MvpState presenterState) {
        if (presenterState instanceof JointToSprintRequest) {
            registerMember((JointToSprintRequest) presenterState);
            return;
        }

        if (presenterState instanceof JointToSprintSuccessResponse) {
            updateViewState(new JoinToSprintModelStateSuccess());
            return;
        }

        if (presenterState instanceof JointToSprintFailResponse) {
            AppException exception = ((JointToSprintFailResponse) presenterState).getException();
            updateViewState(new JoinToSprintModelStateError(exception));
        }
    }

    private void registerMember(@NonNull JointToSprintRequest presenterState) {
        updateViewState(new JoinToSprintModelStateProgress());

        authRepository.registerMemberToSprint(presenterState.getName(),
                presenterState.getSprintToken(),
                this
        );
    }
}
