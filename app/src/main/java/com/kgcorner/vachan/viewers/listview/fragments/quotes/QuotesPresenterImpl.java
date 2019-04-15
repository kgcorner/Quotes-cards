package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import android.util.Log;

import com.kgcorner.sdk.models.Quote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuotesPresenterImpl implements QuotesPresenter {

    private static final String TAG = "QuotesPresenterImpl";
    private QuotesView view;
    private QuotesInteractor interactor;
    private List<Quote> quotes;
    private int page = 1;

    public QuotesPresenterImpl(QuotesInteractor interactor) {
        this.interactor = interactor;
        this.quotes = new ArrayList<>();
    }

    @Override
    public void getQuotes(int page) {
        this.interactor.getQuotes(this.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQuotesLoadSuccess, this::onQuotesLoadFailure);
    }

    private void onQuotesLoadSuccess(List<Quote> quotes) {
        if(quotes == null) {
            this.quotes = quotes;
        } else {
            this.quotes.addAll(quotes);
        }
        if(isViewAttached())
            view.loadQuotes(this.quotes);
    }

    private void onQuotesLoadFailure(Throwable e) {
        Log.e(TAG, "onQuotesLoadFailure: " + e.getLocalizedMessage(), e);
        if(isViewAttached())
            view.showError(e);
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }

    @Override
    public void setView(QuotesView view) {
        this.view = view;
    }
}
