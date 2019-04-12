package com.kgcorner.vachan.viewers.listview.fragments.topics;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Topic;

import java.util.List;

import javax.inject.Inject;

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
