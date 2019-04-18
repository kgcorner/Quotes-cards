package com.kgcorner.vachan.viewers.quotes.fragments.quotes;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.viewers.BaseView;

import java.util.List;

public interface QuotesView extends BaseView {
    void loadQuotes(List<Quote> quotes);
    void loadFavourites(List<Quote> quotes);
}
