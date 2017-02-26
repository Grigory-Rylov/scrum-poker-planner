package com.grishberg.mvvmstatelessexample.di;

import com.grishberg.mvvmstatelessexample.di.components.AppComponent;

/**
 * Created by grishberg on 26.02.17.
 */

public class DiManager {
    private static AppComponent appComponent;

    private DiManager() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    public static void initComponents(final AppComponent component) {
        appComponent = component;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
