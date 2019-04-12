package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import com.kgcorner.sdk.VachanService;

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
}
