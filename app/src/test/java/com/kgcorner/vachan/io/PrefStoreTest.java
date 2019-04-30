package com.kgcorner.vachan.io;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

public class PrefStoreTest {

    private PrefStore prefStore;
    private Context mockedContext;
    private SharedPreferences mockedPref;
    private static final String LATEST_PAGE = "LATEST_PAGE";
    private static final String PREF_NAME = "LatestPageStore";


    @Before
    public void getInstance() {
        mockedContext = Mockito.mock(Context.class);
        Context mockedAppContext = Mockito.mock(Context.class);
        mockedPref = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getApplicationContext()).thenReturn(mockedAppContext);
        Mockito.when(mockedAppContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE))
                .thenReturn(mockedPref);
        prefStore = PrefStore.getInstance(mockedContext);
    }

    @Test
    public void setLatestFetchPage() {
        SharedPreferences.Editor mockedEditor = Mockito.mock(SharedPreferences.Editor.class);
        Mockito.when(mockedPref.edit()).thenReturn(mockedEditor);
        prefStore.setLatestFetchPage(1);
        int latestPage = Whitebox.getInternalState(prefStore, "latestPage");
        Assert.assertEquals("latest page is not matching", 1, latestPage);
    }

    @Test
    public void getLatestFetchedPage() {
        SharedPreferences.Editor mockedEditor = Mockito.mock(SharedPreferences.Editor.class);
        Mockito.when(mockedPref.edit()).thenReturn(mockedEditor);
        prefStore.setLatestFetchPage(1);
        int latestPage = prefStore.getLatestFetchedPage();
        Assert.assertEquals("latest page is not matching", 1, latestPage);
    }

    @Test
    public void getInstance1() {
        PrefStore prefStore = PrefStore.getInstance();
        Assert.assertNotNull("PrefStore is not null", prefStore);
    }
}