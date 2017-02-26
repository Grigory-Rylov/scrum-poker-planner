package com.grishberg.mvvmstatelessexample.di.modules;

import android.content.Context;

import com.grishberg.mvvmstatelessexample.utils.LogCatLogger;
import com.grishberg.mvvmstatelessexample.utils.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 26.02.17.
 */
@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    Logger provideLogService() {
        return new LogCatLogger();
    }
}
