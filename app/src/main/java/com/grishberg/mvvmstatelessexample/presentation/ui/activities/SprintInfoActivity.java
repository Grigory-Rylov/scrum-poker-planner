package com.grishberg.mvvmstatelessexample.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.grishberg.mvpstatelibrary.framework.ui.BaseMvpActivity;
import com.grishberg.mvvmstatelessexample.R;
import com.grishberg.mvvmstatelessexample.presentation.presenters.SprintInfoPresenter;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoState;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoState.RequestSprintInfo;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoViewState;
import com.grishberg.mvvmstatelessexample.presentation.state.sprint.SprintInfoViewState.*;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoActivity extends BaseMvpActivity<SprintInfoPresenter, SprintInfoViewState> {

    private TextView sprintName;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SprintInfoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprint_info_activity);
        sprintName = (TextView) findViewById(R.id.sprint_info_name);
        getPresenter().updateState(new RequestSprintInfo());
    }

    @Override
    protected SprintInfoPresenter createPresenter() {
        return new SprintInfoPresenter();
    }

    @Override
    public void onModelUpdated(SprintInfoViewState state) {
        if (state instanceof SprintInfoSuccess) {
            showSprintState((SprintInfoSuccess) state);
            return;
        }
    }

    private void showSprintState(@NonNull SprintInfoSuccess state) {
        sprintName.setText(state.getSprintName());
    }
}
