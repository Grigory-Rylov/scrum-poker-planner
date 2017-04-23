package com.grishberg.mvvmstatelessexample.domain.repository;

import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.state.StateReceiver;

/**
 * Created by grishberg on 26.02.17.
 */
@FunctionalInterface
public interface AuthRepository {
    void registerMemberToSprint(String name, String sprintToken, final StateReceiver<MvpState> presenter);
}
