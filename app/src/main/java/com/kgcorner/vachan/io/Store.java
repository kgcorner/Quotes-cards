package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

@Singleton
public class Store {

    private final Realm realm;

    /**
     * Creates instance of Store
     * @param realm
     */
    @Inject
    public Store(Realm realm) {
        this.realm = realm;
    }

    /**
     * returns true if quote is listed in favourites
     * @param quote
     * @return
     */
    public boolean isFavourite(Quote quote) {
        return realm.where(QuoteRealMObject.class)
                .equalTo(QuoteRealMObject.ID, quote.getId()).findFirst() != null;
    }

    /**
     * Returns list of favourite quotes
     * @return
     */
    public List<Quote> getFavouriteQuotes() {
        RealmResults<QuoteRealMObject> quoteRealMObjects = realm.where(QuoteRealMObject.class).findAll();
        List<Quote> quotes = new ArrayList<>();
        for(QuoteRealMObject object : quoteRealMObjects) {
            quotes.add(object.readlMToQuote());
        }
        return quotes;
    }

    /**
     * Add quotes to list of favourite quotes
     * @param quote
     */
    public void addToFavourite(Quote quote) {
        QuoteRealMObject object = new QuoteRealMObject(quote);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    /**
     * Remove a quote from list of favourite quote
     * @param quote
     */
    public void removeFromFavourite(Quote quote) {
        realm.beginTransaction();
        QuoteRealMObject object = realm.where(QuoteRealMObject.class).equalTo(QuoteRealMObject.ID, quote.getId()).findFirst();
        if(object != null) {
            object.deleteFromRealm();
        }
        realm.commitTransaction();
    }
}
