package com.kgcorner.vachan.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.kgcorner.sdk.models.Quote;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * FileStore is responsible for favorite quotes by writing them on physical storage
 */
public class FileStore {

    private static final String FAV_FILE = "fav.ser";
    private static final String LATEST_PAGE = "LATEST_PAGE";
    private static final Object obj = new Object();

    private static final String TAG = "FileStore";
    private static final String PREF_NAME = "LatestPageStore";
    private Context context;

    private static FileStore instance;
    private SharedPreferences sharedPreferences;
    private int latestPage;

    private FileStore(){

    }

    private List<Quote> favQuotes;

    /**
     * Return instance of FileStore
     * @param context
     * @return
     */
    public static FileStore getInstance(Context context) {
        if(instance != null) {
            return  instance;
        }
        instance = new FileStore();
        instance.context = context;
        if(instance.fileExists(FAV_FILE)) {
            try {
                instance.favQuotes = (List<Quote>) instance.readData(FAV_FILE);
            } catch (Exception x) {
                Log.e(TAG, "getInstance: "+x.getLocalizedMessage(), x);
            }
        }
        if(instance.favQuotes == null)
            instance.favQuotes = new ArrayList<>();

        instance.sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return instance;
    }

    public static FileStore getInstance() {
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

    public void saveFavQuotes() {
        Log.d(TAG, "saveFavQuotes: Saving quotes");
        writeData(favQuotes, FAV_FILE);
        Log.d(TAG, "saveFavQuotes: Saved quotes");
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

    /**
     * Adds a quote to list of fav quotes
     * @param quote
     */
    public void addToFav(Quote quote) {
        favQuotes.add(quote);
        new Thread() {
            @Override
            public void run() {
                writeData(favQuotes, FAV_FILE);
            }
        }.start();
    }


    /**
     * Removes quote from list of fav quotes
     * @param quote
     */
    public void removeFromFav(Quote quote) {
        favQuotes.remove(quote);
        new Thread() {
            @Override
            public void run() {
                writeData(favQuotes, FAV_FILE);
            }
        }.start();
    }

    /**
     * Returns saved fav quotes
     * @return
     */
    public List<Quote> getFavQuotes() {
        if(favQuotes == null)
            return Collections.emptyList();
        return Collections.unmodifiableList(favQuotes);
    }

    /**
     * Check if file exists or not
     * @param filename name of the file to search for
     * @return true if it exists false otherwise
     */
    private boolean fileExists(String filename)
    {
        boolean exists = true;
        if(context!= null && filename != null) {
            File file = context.getFileStreamPath(filename);
            if (file == null || !file.exists()) {
                exists = false;
            }
        }
        else {
            exists = false;
        }
        return exists;
    }


    /**
     * Read a file and return Serialized Object
     * @param fileName name of the file to read data from
     * @return Object read from the file
     *
     * @throws FileReadFailedException
     *
     */
    private Object readData(String fileName) throws FileReadFailedException {

        //TODO: Check for serialization vunrabilities
        synchronized (obj) {
            ObjectInputStream input = null;
            Object object = null;
            if (fileExists(fileName)) {
                try {
                    input = new ObjectInputStream(context.openFileInput(fileName));
                    object = input.readObject();
                    Log.d(TAG, "Writable object has been loaded from file "+fileName);
                }
                catch(Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    throw new FileReadFailedException(e.getMessage());
                }
                finally {
                    try {
                        if (input != null)
                            input.close();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            }
            return object;
        }
    }

    /**
     * Delete a given file
     * @param filename name of the file to delete
     */
    private void deleteFile(String filename)
    {
        if(context != null) {
            File file = context.getFileStreamPath(filename);
            if (file != null || file.exists())
                file.delete();
        }
    }

    /**
     * Writes data to a file
     * @param data data to write
     * @param filename name of the file with path
     */
    private void writeData(Object data, String filename)
    {
        synchronized (obj) {
            ObjectOutput out = null;
            deleteFile(filename);
            try {
                Log.d(TAG, "Attempting to write Object ");
                out = new ObjectOutputStream(context.openFileOutput(filename, Context.MODE_PRIVATE));
                out.writeObject(data);
                Log.d(TAG, "writableObject written successfully");
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            } finally {
                if (out != null)
                    try {
                        out.close();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
            }
        }
    }
}
