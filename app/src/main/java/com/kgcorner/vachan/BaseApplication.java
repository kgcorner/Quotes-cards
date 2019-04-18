package com.kgcorner.vachan;


import android.app.Application;

import com.kgcorner.vachan.io.FileStore;

import io.realm.Realm;

/**
 * Base application class for entire android client
 */
public class BaseApplication extends Application {
    private AppComponent appComponent;
    private FileStore fileStore;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
        fileStore = FileStore.getInstance(getBaseContext());
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
     * Returns {@link FileStore} instance
     * @return
     */
    public FileStore getFileStore() {
        return fileStore;
    }

    /**
     * Registers a shutdown hook
     */
    private void registerShutDownHook () {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                fileStore.saveFavQuotes();
            }
        });
    }
}
