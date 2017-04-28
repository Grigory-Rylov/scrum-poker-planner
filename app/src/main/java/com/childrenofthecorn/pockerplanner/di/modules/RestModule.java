package com.childrenofthecorn.pockerplanner.di.modules;

import com.childrenofthecorn.pockerplanner.domain.exceptions.ErrorParser;
import com.childrenofthecorn.pockerplanner.domain.model.rest.RestResponse;
import com.childrenofthecorn.pockerplanner.domain.repository.Api;
import com.github.grigoryrylov.reststacklib.RetrofitBuilder;
import com.github.grigoryrylov.reststacklib.SoftErrorDelegate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 26.02.17.
 */

@Module
public class RestModule {
    private final String baseUrl;

    // Constructor needs one parameter to instantiate.
    public RestModule(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Api provideRetrofitService(final SoftErrorDelegate<RestResponse> softErrorDelegate) {
        RetrofitBuilder<Api> builder = new RetrofitBuilder<>(baseUrl, Api.class, softErrorDelegate);
        return builder.provideService();
    }

    @Provides
    @Singleton
    SoftErrorDelegate<RestResponse> provideSoftErrorDelegate() {
        return new ErrorParser();
    }
}
