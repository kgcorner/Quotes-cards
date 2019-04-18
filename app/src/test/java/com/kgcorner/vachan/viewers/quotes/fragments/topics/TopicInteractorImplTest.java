package com.kgcorner.vachan.viewers.quotes.fragments.topics;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Topic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class TopicInteractorImplTest {

    private TopicInteractor interactor;
    private VachanService service;

    @Before
    public void setUp() throws Exception {
        service = Mockito.mock(VachanService.class);
        interactor = new TopicInteractorImpl(service);
    }

    @Test
    public void fetchTopics() {
        List<Topic> topics = new ArrayList<>();
        Observable<List<Topic>> list = Observable.fromArray(topics);
        Mockito.when(service.getTopics()).thenReturn(list);
        Assert.assertNotNull(interactor.fetchTopics());
    }
}