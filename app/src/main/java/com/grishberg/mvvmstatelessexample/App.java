package com.grishberg.mvvmstatelessexample;

import android.app.Application;
import android.os.StrictMode;

import com.grishberg.mvvmstatelessexample.di.DiManager;
import com.grishberg.mvvmstatelessexample.di.components.DaggerAppComponent;
import com.grishberg.mvvmstatelessexample.di.modules.AppModule;
import com.grishberg.mvvmstatelessexample.di.modules.RepositoryModule;
import com.grishberg.mvvmstatelessexample.di.modules.RestModule;
import com.grishberg.mvvmstatelessexample.di.modules.StorageModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by grishberg on 26.02.17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
            // Normal app init code..
            initStrictMode();
        }
        DiManager.initComponents(DaggerAppComponent
                .builder()
                .storageModule(new StorageModule())
                .appModule(new AppModule(this))
                .restModule(new RestModule(getString(R.string.end_point)))
                .repositoryModule(new RepositoryModule())
                .build()
        );
    }

    private void initStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
