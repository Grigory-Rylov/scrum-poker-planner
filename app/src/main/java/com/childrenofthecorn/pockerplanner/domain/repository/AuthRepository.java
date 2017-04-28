package com.childrenofthecorn.pockerplanner.domain.repository;

import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.framework.state.StateReceiver;

/**
 * Created by grishberg on 26.02.17.
 */
@FunctionalInterface
public interface AuthRepository {
    void registerMemberToSprint(String name,
                                String sprintToken,
                                final StateReceiver<PresenterState> presenter);
}
