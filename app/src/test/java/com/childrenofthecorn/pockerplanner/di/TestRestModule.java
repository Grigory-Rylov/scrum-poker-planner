package com.childrenofthecorn.pockerplanner.di;

import com.childrenofthecorn.pockerplanner.domain.repository.Api;
import com.childrenofthecorn.pockerplanner.domain.repository.AuthRepository;
import com.childrenofthecorn.pockerplanner.domain.repository.SprintRepository;
import com.childrenofthecorn.pockerplanner.domain.storage.AuthTokenStorage;
import com.childrenofthecorn.pockerplanner.utils.Logger;
import com.childrenofthecorn.pockerplanner.utils.LoggerImpl;

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

    @Singleton
    @Provides
    SprintRepository provideSprintResository(final Api api, final AuthTokenStorage tokenStorage) {
        return Mockito.mock(SprintRepository.class);
    }
}

