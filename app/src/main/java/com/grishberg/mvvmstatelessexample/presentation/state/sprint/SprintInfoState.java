package com.grishberg.mvvmstatelessexample.presentation.state.sprint;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException;

import java.util.Date;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoState implements MvpState {

    public static class RequestSprintInfo extends SprintInfoState {
    }

    @Getter
    public static class ReceiveSprintInfo extends SprintInfoState {
        private final String name;
        private final Date date;

        public ReceiveSprintInfo(String name, Date date) {
            this.name = name;
            this.date = date;
        }
    }

    public static class ReceiveCurrentTaskState extends SprintInfoState {
        private final String name;
        private final String description;

        public ReceiveCurrentTaskState(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    public static class ReceiveErrorState extends SprintInfoState {
        private final AppException exception;

        public ReceiveErrorState(AppException exception) {
            this.exception = exception;
        }
    }
}
