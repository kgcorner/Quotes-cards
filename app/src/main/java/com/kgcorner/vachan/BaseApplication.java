package com.kgcorner.vachan;


import android.app.Application;

import com.kgcorner.vachan.io.Store;

/**
 * Base application class for entire android client
 */
public class BaseApplication extends Application {
    private AppComponent appComponent;
    private Store store;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
        store = Store.getInstance(getBaseContext());
        registerShutDownHook();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * Returns {@link Store} instance
     * @return
     */
    public Store getStore() {
        return store;
    }

    /**
     * Registers a shutdown hook
     */
    private void registerShutDownHook () {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                store.saveFavQuotes();
            }
        });
    }
}
