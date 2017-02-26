package com.grishberg.mvvmstatelessexample.di.modules;

import com.grishberg.mvvmstatelessexample.domain.repository.Api;
import com.grishberg.mvvmstatelessexample.domain.repository.AuthRepository;
import com.grishberg.mvvmstatelessexample.domain.repository.AuthRepositoryImpl;
import com.grishberg.mvvmstatelessexample.domain.storage.AuthTokenStorage;

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
}
