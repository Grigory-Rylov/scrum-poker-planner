package com.grishberg.mvvmstatelessexample.presentation.state.auth;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */

@Getter
public class JointToSprintViewState implements MvpState {

    public static class JoinToSprintModelStateProgress extends JointToSprintViewState {
    }

    public static class JoinToSprintModelStateSuccess extends JointToSprintViewState {
    }

    @Getter
    public static class JoinToSprintModelStateError extends JointToSprintViewState {
        private final AppException exception;

        public JoinToSprintModelStateError(AppException exception) {
            this.exception = exception;
        }
    }
}
