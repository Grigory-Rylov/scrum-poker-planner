package com.grishberg.mvvmstatelessexample.domain.storage;

import com.grishberg.mvvmstatelessexample.domain.model.UserContainer;

import rx.Observable;
import rx.Single;

/**
 * Created by grishberg on 26.02.17.
 */

public interface AuthTokenStorage {
    void storeCurrentCredential(String sprintToken, UserContainer data);

    Observable<String> requestSprintToken();

    Observable<String> requestAccessToken();

    Observable<String> requestUserName();
}
