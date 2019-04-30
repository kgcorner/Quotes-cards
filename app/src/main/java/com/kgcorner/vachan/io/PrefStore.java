package com.kgcorner.vachan.io;

import android.content.Context;
import android.content.SharedPreferences;

import com.kgcorner.sdk.models.Quote;

import java.util.List;
/**
 * PrefStore is responsible for favorite quotes by writing them on physical storage
 */
public class PrefStore {

    private static final String FAV_FILE = "fav.ser";
    private static final String LATEST_PAGE = "LATEST_PAGE";
    private static final Object obj = new Object();

    private static final String TAG = "PrefStore";
    private static final String PREF_NAME = "LatestPageStore";
    private Context context;

    private static PrefStore instance;
    private SharedPreferences sharedPreferences;
    private int latestPage;

    private PrefStore(){

    }

    private List<Quote> favQuotes;

    /**
     * Return instance of PrefStore
     * @param context
     * @return
     */
    public static PrefStore getInstance(Context context) {
        if(instance != null) {
            return  instance;
        }
        instance = new PrefStore();
        instance.context = context;
        instance.sharedPreferences = context.getApplicationContext()
                                            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return instance;
    }

    public static PrefStore getInstance() {
        return instance;
    }
    /**
     * Set the last read page from Vachan Server
     * @param page
     */
    public void setLatestFetchPage(int page) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LATEST_PAGE, page);
        editor.apply();
        latestPage = page;
    }

    /**
     * Get the last read page from Vachan Server
     * @return
     */
    public int getLatestFetchedPage() {
        if(latestPage == 0)
            return sharedPreferences.getInt(LATEST_PAGE, 1);
        else
            return latestPage;
    }
}
