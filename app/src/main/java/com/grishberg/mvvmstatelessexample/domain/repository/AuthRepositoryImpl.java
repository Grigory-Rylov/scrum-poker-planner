package com.grishberg.mvvmstatelessexample.domain.repository;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvpstatelibrary.framework.state.StateReceiver;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException;
import com.grishberg.mvvmstatelessexample.domain.storage.AuthTokenStorage;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState.*;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 26.02.17.
 */

public class AuthRepositoryImpl implements AuthRepository {
    private final Api api;
    private final AuthTokenStorage authTokenStorage;

    public AuthRepositoryImpl(Api api, AuthTokenStorage tokenStorage) {
        this.api = api;
        this.authTokenStorage = tokenStorage;
    }

    @Override
    public void registerMemberToSprint(final String name,
                                       final String sprintToken,
                                       final StateReceiver<MvpState> presenter) {
        api.registerMember(name, sprintToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(response -> {
                    authTokenStorage.storeCurrentCredential(sprintToken, response.getData());
                    return Observable.just(true);
                })
                .subscribe(
                        response -> presenter.updateState(new JoinToSprintModelStateSuccess()),
                        exception -> {
                            if (exception instanceof AppException) {
                                presenter.updateState(new JoinToSprintModelStateError((AppException) exception));
                            }
                        }
                );
    }
}
