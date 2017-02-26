package com.grishberg.mvvmstatelessexample.di.components;

import com.grishberg.mvvmstatelessexample.di.modules.AppModule;
import com.grishberg.mvvmstatelessexample.di.modules.RepositoryModule;
import com.grishberg.mvvmstatelessexample.di.modules.RestModule;
import com.grishberg.mvvmstatelessexample.di.modules.StorageModule;
import com.grishberg.mvvmstatelessexample.presentation.presenters.JoinToSprintPresenter;
import com.grishberg.mvvmstatelessexample.presentation.presenters.SprintInfoPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 26.02.17.
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
