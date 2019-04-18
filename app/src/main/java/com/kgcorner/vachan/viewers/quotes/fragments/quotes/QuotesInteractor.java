package com.kgcorner.vachan.viewers.quotes.fragments.quotes;

import com.kgcorner.sdk.models.Quote;

import java.util.List;

import io.reactivex.Observable;

public interface QuotesInteractor {
    Observable<List<Quote>> getQuotes(int page);
}
