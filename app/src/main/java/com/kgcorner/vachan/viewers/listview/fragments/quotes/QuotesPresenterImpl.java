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

    public QuotesPresenterImpl(QuotesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void getQuotes(int page) {
        this.interactor.getQuotes(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQuotesLoadSuccess, this::onQuotesLoadFailure);
    }

    private void onQuotesLoadSuccess(List<Quote> quotes) {
        if(isViewAttached())
            view.loadQuotes(quotes);
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
