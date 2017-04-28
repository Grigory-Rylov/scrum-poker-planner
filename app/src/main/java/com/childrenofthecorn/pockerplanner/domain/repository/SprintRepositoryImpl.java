package com.childrenofthecorn.pockerplanner.domain.repository;

import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.framework.state.StateReceiver;
import com.childrenofthecorn.pockerplanner.domain.exceptions.AppException;
import com.childrenofthecorn.pockerplanner.domain.storage.AuthTokenStorage;
import com.childrenofthecorn.pockerplanner.presentation.state.sprint.SprintInfoState.ReceiveErrorState;
import com.childrenofthecorn.pockerplanner.presentation.state.sprint.SprintInfoState.ReceiveSprintInfo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 27.02.17.
 */

public class SprintRepositoryImpl implements SprintRepository {
    private final Api api;
    private final AuthTokenStorage authTokenStorage;

    public SprintRepositoryImpl(Api api, AuthTokenStorage authTokenStorage) {
        this.api = api;
        this.authTokenStorage = authTokenStorage;
    }

    @Override
    public void getSprintInfo(final StateReceiver<PresenterState> presenter) {
        authTokenStorage.requestSprintToken()
                .flatMap(sprintToken -> api.getSprintInfo(sprintToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> presenter.updateState(
                                new ReceiveSprintInfo(response.getData().getName(),
                                        response.getData().getCreationDate())),
                        exception -> {
                            if (exception instanceof AppException) {
                                presenter.updateState(new ReceiveErrorState((AppException) exception));
                            }
                        }
                );
    }
}
