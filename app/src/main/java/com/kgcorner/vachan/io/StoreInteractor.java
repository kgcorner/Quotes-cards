package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import java.util.List;

public interface StoreInteractor {
    /**
     * Adds a quote to favourite
     * @param quote
     */
    void addToFavorite(Quote quote);

    /**
     * Remove a quote from favourite
     * @param quote
     */
    void removeFromFavourite(Quote quote);

    /**
     * get all favourites
     * @return
     */
    List<Quote> getAllFavorite();

    /**
     * returns true if quote is listed in favourites
     * @param quote
     * @return
     */
    boolean isFavourite(Quote quote);
}
