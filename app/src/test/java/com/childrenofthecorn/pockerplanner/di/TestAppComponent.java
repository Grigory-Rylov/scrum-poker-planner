package com.childrenofthecorn.pockerplanner.di;

import com.childrenofthecorn.pockerplanner.di.components.AppComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 26.02.17.
 */

@Singleton
@Component(modules = {TestRestModule.class})
public interface TestAppComponent extends AppComponent {
}
