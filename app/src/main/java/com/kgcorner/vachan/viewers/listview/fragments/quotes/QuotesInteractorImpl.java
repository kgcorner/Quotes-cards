package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Quote;

import java.util.List;

import io.reactivex.Observable;

public class QuotesInteractorImpl implements QuotesInteractor {

    private VachanService service;

    QuotesInteractorImpl(VachanService service) {
        this.service = service;
    }

    @Override
    public Observable<List<Quote>> getQuotes(int page) {
        return service.getQuotes(page);
    }

}
