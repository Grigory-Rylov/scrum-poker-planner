package com.childrenofthecorn.pockerplanner.domain.storage;

import android.content.Context;

import com.childrenofthecorn.pockerplanner.domain.model.UserContainer;
import com.childrenofthecorn.pockerplanner.utils.SharedPreferenceUtils;

import rx.Observable;

/**
 * Created by grishberg on 26.02.17.
 */

public class AuthTokenStorageImpl implements AuthTokenStorage {

    private static final String PREF_SPRINT_TOKEN = "pref_sprint_token";
    private static final String PREF_ACCESS_TOKEN = "pref_access_token";
    private static final String PREF_NAME = "pref_name";
    private SharedPreferenceUtils sharedPreference;

    public AuthTokenStorageImpl(Context context) {
        this.sharedPreference = new SharedPreferenceUtils(context);
    }

    @Override
    public void storeCurrentCredential(String sprintToken, UserContainer userContainer) {
        sharedPreference.putString(PREF_SPRINT_TOKEN, sprintToken);
        sharedPreference.putString(PREF_ACCESS_TOKEN, userContainer.getAccessToken());
        sharedPreference.putString(PREF_NAME, userContainer.getName());
    }

    @Override
    public Observable<String> requestSprintToken() {
        return Observable.just(sharedPreference.getString(PREF_SPRINT_TOKEN));
    }

    @Override
    public Observable<String> requestAccessToken() {
        return Observable.just(sharedPreference.getString(PREF_ACCESS_TOKEN));
    }

    @Override
    public Observable<String> requestUserName() {
        return Observable.just(sharedPreference.getString(PREF_NAME));
    }
}
