package com.childrenofthecorn.pockerplanner.domain.storage;

import com.childrenofthecorn.pockerplanner.domain.model.UserContainer;

import rx.Observable;

/**
 * Created by grishberg on 26.02.17.
 */

public interface AuthTokenStorage {
    void storeCurrentCredential(String sprintToken, UserContainer data);

    Observable<String> requestSprintToken();

    Observable<String> requestAccessToken();

    Observable<String> requestUserName();
}
