package com.grishberg.mvvmstatelessexample.domain.repository;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvpstatelibrary.framework.state.StateReceiver;

/**
 * Created by grishberg on 26.02.17.
 */

public interface SprintRepository {
    void getSprintInfo(final StateReceiver<MvpState> presenter);
}
