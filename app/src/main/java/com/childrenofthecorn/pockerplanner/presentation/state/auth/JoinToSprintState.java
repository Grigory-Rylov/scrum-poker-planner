package com.childrenofthecorn.pockerplanner.presentation.state.auth;

import com.github.mvpstatelib.framework.state.AbsPresenterState;
import com.childrenofthecorn.pockerplanner.domain.exceptions.AppException;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */
@Getter
public class JoinToSprintState extends AbsPresenterState {
    @Getter
    public static class JointToSprintRequest extends JoinToSprintState {
        private final String name;
        private final String sprintToken;

        public JointToSprintRequest(String name, String sprintToken) {
            this.name = name;
            this.sprintToken = sprintToken;
        }
    }

    public static class JointToSprintSuccessResponse extends JoinToSprintState {
    }

    @Getter
    public static class JointToSprintFailResponse extends JoinToSprintState {
        private final AppException exception;

        public JointToSprintFailResponse(AppException exception) {
            this.exception = exception;
        }
    }
}
