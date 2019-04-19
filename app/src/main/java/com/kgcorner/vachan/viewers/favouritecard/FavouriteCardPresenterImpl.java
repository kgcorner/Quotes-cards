package com.kgcorner.vachan.viewers.favouritecard;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.io.StoreInteractor;

import java.util.List;

import javax.inject.Inject;


public class FavouriteCardPresenterImpl implements FavouriteCardPresenter {

    private final StoreInteractor interactor;

    public FavouriteCardPresenterImpl(StoreInteractor interactor) {

        this.interactor = interactor;
    }

    @Override
    public List<Quote> getFavCards() {
        return interactor.getAllFavorite();
    }
}
