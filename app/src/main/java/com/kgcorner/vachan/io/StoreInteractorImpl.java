package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import java.util.List;

public class StoreInteractorImpl implements StoreInteractor {

    private final Store store;

    public StoreInteractorImpl(Store store) {

        this.store = store;
    }

    @Override
    public void addToFavorite(Quote quote) {
        store.addToFavourite(quote);
    }

    @Override
    public boolean isFavourite(Quote quote) {
        return store.isFavourite(quote);
    }

    @Override
    public void removeFromFavourite(Quote quote) {
        store.removeFromFavourite(quote);
    }

    @Override
    public List<Quote> getAllFavorite() {
        return store.getFavouriteQuotes();
    }
}
