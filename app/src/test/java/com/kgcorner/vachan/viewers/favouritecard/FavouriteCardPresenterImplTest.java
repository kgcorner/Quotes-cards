package com.kgcorner.vachan.viewers.favouritecard;

import com.kgcorner.vachan.io.StoreInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class FavouriteCardPresenterImplTest {

    private FavouriteCardPresenter presenter;
    private StoreInteractor storeInteractor;
    @Before
    public void init() {
        storeInteractor = Mockito.mock(StoreInteractor.class);
        presenter = new FavouriteCardPresenterImpl(storeInteractor);
    }

    @Test
    public void getFavCards() {
        presenter.getFavCards();
        Mockito.verify(storeInteractor).getAllFavorite();
    }
}