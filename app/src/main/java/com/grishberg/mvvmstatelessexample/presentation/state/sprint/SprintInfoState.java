package com.grishberg.mvvmstatelessexample.presentation.state.sprint;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoState implements MvpState {

    public static class ReceiveCurrentTaskState extends SprintInfoState {
        private final String name;
        private final String description;

        public ReceiveCurrentTaskState(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    public static class ReceiveErrorState extends SprintInfoState {
    }
}
