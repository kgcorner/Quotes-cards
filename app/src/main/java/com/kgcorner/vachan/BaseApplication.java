package com.kgcorner.vachan;


import android.app.Application;

import com.kgcorner.vachan.io.PrefStore;

import io.realm.Realm;

/**
 * Base application class for entire android client
 */
public class BaseApplication extends Application {
    private AppComponent appComponent;
    private PrefStore prefStore;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
        prefStore = PrefStore.getInstance(getBaseContext());
        initializeRealM();
        registerShutDownHook();
    }

    private void initializeRealM() {
        Realm.init(this);
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * Returns {@link PrefStore} instance
     * @return
     */
    public PrefStore getPrefStore() {
        return prefStore;
    }

    /**
     * Registers a shutdown hook
     */
    private void registerShutDownHook () {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                //prefStore.saveFavQuotes();
            }
        });
    }
}
