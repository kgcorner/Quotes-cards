package com.kgcorner.vachan.io;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Initializes utilities for store management
 */
@Module
public abstract class StoreModule {

    @Provides
    @Singleton
    public static Realm providesRealM() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public static StoreInteractor provideStoreInteractor(Store store) {
        return new StoreInteractorImpl(store);
    }
}
