package com.kgcorner.vachan.viewers.selectedtopics;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Quote;

import java.util.List;

import io.reactivex.Observable;

public class CardOnTopicInteractorImpl implements CardOnTopicInteractor {

    private final VachanService service;

    public CardOnTopicInteractorImpl(VachanService service) {

        this.service = service;
    }

    public Observable<List<Quote>> getQuotesOnTopics(String topics, int page) {
        return service.getQuotes(topics, page);
    }
}
