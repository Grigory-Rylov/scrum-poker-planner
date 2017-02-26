package com.grishberg.mvvmstatelessexample.di;

import com.grishberg.mvvmstatelessexample.domain.repository.Api;
import com.grishberg.mvvmstatelessexample.domain.repository.AuthRepository;
import com.grishberg.mvvmstatelessexample.domain.storage.AuthTokenStorage;
import com.grishberg.mvvmstatelessexample.utils.Logger;
import com.grishberg.mvvmstatelessexample.utils.LoggerImpl;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 26.02.17.
 */

@Module
public class TestRestModule {

    private final Api api;

    public TestRestModule(final Api api) {
        this.api = api;
    }

    @Provides
    @Singleton
    Logger provideLogger() {
        return new LoggerImpl();
    }

    @Provides
    @Singleton
    Api provideApi() {
        return api;
    }

    @Provides
    @Singleton
    AuthTokenStorage provideAuthTokenStorage() {
        return Mockito.mock(AuthTokenStorage.class);
    }

    @Provides
    @Singleton
    AuthRepository provideAuthRepository() {
        return Mockito.mock(AuthRepository.class);
    }
}

