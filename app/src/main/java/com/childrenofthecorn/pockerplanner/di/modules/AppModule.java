package com.childrenofthecorn.pockerplanner.di.modules;

import android.content.Context;

import com.childrenofthecorn.pockerplanner.utils.LogCatLogger;
import com.childrenofthecorn.pockerplanner.utils.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 26.02.17.
 * Common dependency
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
