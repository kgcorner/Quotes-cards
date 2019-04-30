package com.kgcorner.vachan.io;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import io.realm.Realm;

import static org.junit.Assert.*;

@PrepareForTest(Realm.class)
public class StoreModuleTest {

    @Test
    public void provideStoreInteractor() {
        Store store = Mockito.mock(Store.class);
        Assert.assertNotNull(StoreModule.provideStoreInteractor(store));
    }
}