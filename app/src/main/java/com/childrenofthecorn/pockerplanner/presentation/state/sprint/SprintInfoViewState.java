package com.childrenofthecorn.pockerplanner.presentation.state.sprint;

import com.github.mvpstatelib.framework.state.AbsViewState;
import com.childrenofthecorn.pockerplanner.domain.exceptions.AppException;

import java.util.Date;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoViewState extends AbsViewState {
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
