package com.grishberg.mvvmstatelessexample.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.grishberg.mvpstatelibrary.framework.ui.BaseMvpActivity;
import com.grishberg.mvvmstatelessexample.R;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException.*;
import com.grishberg.mvvmstatelessexample.presentation.presenters.JoinToSprintPresenter;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState.*;

public class JointToSprintActivity extends BaseMvpActivity<JoinToSprintPresenter, JointToSprintViewState> {

    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.joint_to_sprint_progress);
    }

    @Override
    protected JoinToSprintPresenter createPresenter() {
        return new JoinToSprintPresenter();
    }

    @Override
    public void onModelUpdated(JointToSprintViewState state) {
        if (state instanceof JoinToSprintModelStateProgress) {
            showProgress(true);
            return;
        }
        if (state instanceof JoinToSprintModelStateSuccess) {
            showSprintScreen();
            return;
        }
        if (state instanceof JoinToSprintModelStateError) {
            showError((JoinToSprintModelStateError) state);
        }
    }

    private void showProgress(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showSprintScreen() {
        SprintInfoActivity.start(this);
        finish();
    }

    private void showError(@NonNull JoinToSprintModelStateError exception) {
        showProgress(false);
        if (exception.getException() instanceof WrongAccessTokenException) {
            Toast.makeText(this, R.string.joint_to_sprint_bad_sprint_token, Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (exception.getException() instanceof NoInternetException) {
            Toast.makeText(this, R.string.error_no_internet, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
