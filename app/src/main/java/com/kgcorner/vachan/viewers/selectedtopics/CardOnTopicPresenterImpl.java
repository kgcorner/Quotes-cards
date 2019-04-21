package com.kgcorner.vachan.viewers.selectedtopics;

import com.kgcorner.sdk.models.Quote;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CardOnTopicPresenterImpl implements CardOnTopicPresenter {
    private final CardOnTopicInteractor interactor;
    private int page = 0;
    private CardOnTopicView view;

    public CardOnTopicPresenterImpl(CardOnTopicInteractor interactor) {

        this.interactor = interactor;
    }

    @Override
    public void fetchQuotes(String topic) {
        page++;
        interactor.getQuotesOnTopics(topic, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadQuotes, this::onFailure);
    }

    private void onFailure(Throwable throwable) {
        if(view !=null)
            view.showError(throwable);
    }

    private void loadQuotes(List<Quote> quotes) {
        if(view != null)
            view.loadQuotes(quotes);
    }

    @Override
    public void setView(CardOnTopicView view) {
        this.view = view;
    }
}
