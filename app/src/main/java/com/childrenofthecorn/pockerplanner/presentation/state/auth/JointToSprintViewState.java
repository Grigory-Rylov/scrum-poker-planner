package com.childrenofthecorn.pockerplanner.presentation.state.auth;

import com.github.mvpstatelib.framework.state.AbsViewState;
import com.childrenofthecorn.pockerplanner.domain.exceptions.AppException;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */

@Getter
public class JointToSprintViewState extends AbsViewState {

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
