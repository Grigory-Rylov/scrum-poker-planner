package com.childrenofthecorn.pockerplanner.utils;

import com.childrenofthecorn.pockerplanner.di.DaggerTestAppComponent;
import com.childrenofthecorn.pockerplanner.di.DiManager;
import com.childrenofthecorn.pockerplanner.di.TestRestModule;
import com.childrenofthecorn.pockerplanner.di.components.AppComponent;
import com.childrenofthecorn.pockerplanner.domain.repository.Api;

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

