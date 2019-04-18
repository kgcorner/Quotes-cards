package com.kgcorner.vachan.viewers.quotes.fragments.topics;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Topic;

import java.util.List;

import io.reactivex.Observable;

public class TopicInteractorImpl implements TopicInteractor {


    private final VachanService service;

    public TopicInteractorImpl(VachanService service) {
        this.service = service;
    }

    @Override
    public Observable<List<Topic>> fetchTopics() {
        return service.getTopics();
    }
}
