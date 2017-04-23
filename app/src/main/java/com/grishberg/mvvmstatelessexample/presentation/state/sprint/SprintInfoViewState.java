package com.grishberg.mvvmstatelessexample.presentation.state.sprint;

import com.github.mvpstatelib.framework.state.AbsMvpState;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException;

import java.util.Date;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoViewState extends AbsMvpState {
    @Getter
    public static class SprintInfoSuccess extends SprintInfoViewState {
        private final String sprintName;
        private final Date creationDate;

        public SprintInfoSuccess(String sprintName, Date creationDate) {
            this.sprintName = sprintName;
            this.creationDate = creationDate;
        }
    }

    @Getter
    public static class SprintInfoException extends SprintInfoViewState {
        private final AppException exception;

        public SprintInfoException(AppException exception) {
            this.exception = exception;
        }
    }

    public static class SprinInfoProgress extends SprintInfoViewState {

    }
}
