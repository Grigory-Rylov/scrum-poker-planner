package com.childrenofthecorn.pockerplanner.domain.repository;

import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.framework.state.StateReceiver;
import com.childrenofthecorn.pockerplanner.domain.exceptions.AppException;
import com.childrenofthecorn.pockerplanner.domain.storage.AuthTokenStorage;
import com.childrenofthecorn.pockerplanner.presentation.state.auth.JoinToSprintState.*;

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
                                       final StateReceiver<PresenterState> presenter) {
        api.registerMember(name, sprintToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(response -> {
                    authTokenStorage.storeCurrentCredential(sprintToken, response.getData());
                    return Observable.just(true);
                })
                .subscribe(
                        response -> presenter.updateState(new JointToSprintSuccessResponse()),
                        exception -> {
                            if (exception instanceof AppException) {
                                presenter.updateState(new JointToSprintFailResponse((AppException) exception));
                            }
                        }
                );
    }
}
