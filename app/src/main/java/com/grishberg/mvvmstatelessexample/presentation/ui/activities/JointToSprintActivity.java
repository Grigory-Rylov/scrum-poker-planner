package com.grishberg.mvvmstatelessexample.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.ui.BaseMvpActivity;
import com.grishberg.mvvmstatelessexample.R;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException.*;
import com.grishberg.mvvmstatelessexample.presentation.presenters.JoinToSprintPresenter;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JoinToSprintState.JointToSprintRequest;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState.*;

public class JointToSprintActivity extends BaseMvpActivity<JoinToSprintPresenter> {

    private View progressBar;
    private View joinButton;
    private EditText sprintToken;
    private EditText memberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.auth_screen_progress);
        joinButton = findViewById(R.id.auth_screen_button_enter);
        sprintToken = (EditText) findViewById(R.id.auth_screen_token);
        memberName = (EditText) findViewById(R.id.auth_screen_login);
        initJoinButton();
    }

    private void initJoinButton() {
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().updateState(
                        new JointToSprintRequest(memberName.getText().toString(),
                                sprintToken.getText().toString()));
            }
        });
    }

    @Override
    protected JoinToSprintPresenter createPresenter() {
        return new JoinToSprintPresenter();
    }

    @Override
    public void onStateUpdated(MvpState state) {
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
        joinButton.setEnabled(!isVisible);
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showSprintScreen() {
        SprintInfoActivity.start(this);
        finish();
    }

    private void showError(@NonNull JoinToSprintModelStateError exception) {
        showProgress(false);
        if (exception.getException() instanceof WrongSprintTokenException) {
            Toast.makeText(this, R.string.joint_to_sprint_bad_sprint_token, Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (exception.getException() instanceof NoInternetException) {
            Toast.makeText(this, R.string.error_no_internet, Toast.LENGTH_SHORT)
                    .show();
        }
        if(exception.getException() instanceof UnknownException){
            Toast.makeText(this, R.string.error_unknown, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
