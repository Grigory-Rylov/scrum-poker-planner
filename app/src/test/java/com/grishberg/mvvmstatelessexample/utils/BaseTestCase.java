package com.grishberg.mvvmstatelessexample.utils;

import com.grishberg.mvvmstatelessexample.di.DaggerTestAppComponent;
import com.grishberg.mvvmstatelessexample.di.DiManager;
import com.grishberg.mvvmstatelessexample.di.TestRestModule;
import com.grishberg.mvvmstatelessexample.di.components.AppComponent;
import com.grishberg.mvvmstatelessexample.domain.repository.Api;

import org.junit.Before;
import org.mockito.Mock;

/**
 * Created by grishberg on 26.02.17.
 */

public class BaseTestCase {

    @Mock
    protected Api api;

    @Before
    public void setUp() throws Exception {
        final AppComponent component = DaggerTestAppComponent.builder()
                .testRestModule(new TestRestModule(api))
                .build();
        DiManager.initComponents(component);
    }
}

