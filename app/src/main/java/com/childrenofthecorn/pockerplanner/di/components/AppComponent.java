package com.childrenofthecorn.pockerplanner.di.components;

import com.childrenofthecorn.pockerplanner.di.modules.AppModule;
import com.childrenofthecorn.pockerplanner.di.modules.RepositoryModule;
import com.childrenofthecorn.pockerplanner.di.modules.RestModule;
import com.childrenofthecorn.pockerplanner.di.modules.StorageModule;
import com.childrenofthecorn.pockerplanner.presentation.presenters.JoinToSprintPresenter;
import com.childrenofthecorn.pockerplanner.presentation.presenters.SprintInfoPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 26.02.17.
 * App component for DI
 */
@Singleton
@Component(modules = {
        RestModule.class,
        AppModule.class,
        StorageModule.class,
        RepositoryModule.class
})
public interface AppComponent {
    void inject(JoinToSprintPresenter joinToSprintPresenter);

    void inject(SprintInfoPresenter sprintInfoPresenter);
}
