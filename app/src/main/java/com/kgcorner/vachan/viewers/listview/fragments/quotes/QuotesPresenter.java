package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import com.kgcorner.vachan.viewers.BasePresenter;

public interface QuotesPresenter extends BasePresenter {
    void getQuotes(int page);
    void setView(QuotesView view);
}
