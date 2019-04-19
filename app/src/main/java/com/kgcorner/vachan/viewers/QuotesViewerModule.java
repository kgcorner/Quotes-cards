package com.kgcorner.vachan.viewers;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.io.StoreInteractor;
import com.kgcorner.vachan.viewers.favouritecard.FavouriteCardPresenter;
import com.kgcorner.vachan.viewers.favouritecard.FavouriteCardPresenterImpl;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesInteractor;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesInteractorImpl;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesPresenter;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesPresenterImpl;
import com.kgcorner.vachan.viewers.quotes.images.ImageInteractor;
import com.kgcorner.vachan.viewers.quotes.images.ImageInteractorImpl;
import com.kgcorner.vachan.viewers.quotes.images.ImagePresenter;
import com.kgcorner.vachan.viewers.quotes.images.ImagePresenterImpl;

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


    @Provides
    static ImageInteractor provideImageInteractor(VachanService service) {
        return new ImageInteractorImpl(service);
    }

    @Provides
    static ImagePresenter provideImagePresenter(ImageInteractor interactor) {
        return new ImagePresenterImpl(interactor);
    }
}
