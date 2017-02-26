package com.grishberg.mvvmstatelessexample.di.modules;

import android.content.Context;

import com.grishberg.mvvmstatelessexample.domain.storage.AuthTokenStorage;
import com.grishberg.mvvmstatelessexample.domain.storage.AuthTokenStorageImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 26.02.17.
 */
@Module
public class StorageModule {

    @Singleton
    @Provides
    AuthTokenStorage provideAuthTokenStorage(Context context) {
        return new AuthTokenStorageImpl(context);
    }
}
