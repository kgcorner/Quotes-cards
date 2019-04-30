package com.kgcorner.vachan.viewers.selectedtopics;

import com.kgcorner.sdk.VachanService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CardOnTopicInteractorImplTest {

    private CardOnTopicInteractor interactor;
    private VachanService service;


    @Before
    public void setUp() throws Exception {
        service = Mockito.mock(VachanService.class);
        interactor = new CardOnTopicInteractorImpl(service);
    }

    @Test
    public void getQuotesOnTopics() {
        String topic = "topic";
        int page = 1;
        interactor.getQuotesOnTopics(topic, page);
        Mockito.verify(service).getQuotes(topic, page);
    }
}