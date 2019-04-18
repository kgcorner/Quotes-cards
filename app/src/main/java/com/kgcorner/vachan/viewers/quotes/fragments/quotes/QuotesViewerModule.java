package com.kgcorner.vachan.viewers.quotes.fragments.quotes;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.io.StoreInteractor;
import com.kgcorner.vachan.viewers.favouritecard.FavouriteCardPresenter;
import com.kgcorner.vachan.viewers.favouritecard.FavouriteCardPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class QuotesViewerModule {

    @Provides
    static QuotesInteractor provideQuotesInetractor(VachanService vachanService) {
        return new QuotesInteractorImpl(vachanService);
    }

    @Provides
    static QuotesPresenter providesQuotesPresenter(QuotesInteractor interactor) {
        return new QuotesPresenterImpl(interactor);
    }

    @Provides
    static FavouriteCardPresenter providesFavQuotesPresenter(StoreInteractor interactor) {
        return new FavouriteCardPresenterImpl(interactor);
    }
}
