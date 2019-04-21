package com.kgcorner.vachan.viewers.selectedtopics;

import com.kgcorner.sdk.models.Quote;

import java.util.List;

import io.reactivex.Observable;

public interface CardOnTopicInteractor {
    Observable<List<Quote>> getQuotesOnTopics(String topics, int page);
}
