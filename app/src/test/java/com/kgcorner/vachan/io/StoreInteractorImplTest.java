package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class StoreInteractorImplTest {

    private StoreInteractor interactor;

    private Store mockedStore;

    @Before
    public void setUp() throws Exception {
        mockedStore = Mockito.mock(Store.class);
        interactor = new StoreInteractorImpl(mockedStore);
    }

    @Test
    public void addToFavorite() {
        Quote quote = new Quote();
        interactor.addToFavorite(quote);
        Mockito.verify(mockedStore).addToFavourite(quote);
    }

    @Test
    public void isFavourite() {
        Quote quote = new Quote();
        interactor.isFavourite(quote);
        Mockito.verify(mockedStore).isFavourite(quote);
    }

    @Test
    public void removeFromFavourite() {
        Quote quote = new Quote();
        interactor.removeFromFavourite(quote);
        Mockito.verify(mockedStore).removeFromFavourite(quote);
    }

    @Test
    public void getAllFavorite() {
        interactor.getAllFavorite();
        Mockito.verify(mockedStore).getFavouriteQuotes();
    }
}