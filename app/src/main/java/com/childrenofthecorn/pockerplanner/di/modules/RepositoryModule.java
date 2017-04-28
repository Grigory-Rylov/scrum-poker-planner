package com.childrenofthecorn.pockerplanner.di.modules;

import com.childrenofthecorn.pockerplanner.domain.repository.Api;
import com.childrenofthecorn.pockerplanner.domain.repository.AuthRepository;
import com.childrenofthecorn.pockerplanner.domain.repository.AuthRepositoryImpl;
import com.childrenofthecorn.pockerplanner.domain.repository.SprintRepository;
import com.childrenofthecorn.pockerplanner.domain.repository.SprintRepositoryImpl;
import com.childrenofthecorn.pockerplanner.domain.storage.AuthTokenStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 26.02.17.
 */
@Module
public class RepositoryModule {
    @Singleton
    @Provides
    AuthRepository provideAuthRepository(final Api api, final AuthTokenStorage tokenStorage) {
        return new AuthRepositoryImpl(api, tokenStorage);
    }

    @Singleton
    @Provides
    SprintRepository provideSprintResository(final Api api, final AuthTokenStorage tokenStorage) {
        return new SprintRepositoryImpl(api, tokenStorage);
    }
}
