package com.kgcorner.vachan.viewers.quotes.images;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Topic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ImageInteractorImplTest {

    private ImageInteractor interactor;

    private VachanService service;

    @Before
    public void setUp() throws Exception {
        service = Mockito.mock(VachanService.class);
        interactor = new ImageInteractorImpl(service);
    }

    @Test
    public void fetchImages() {
        String topic = "topic";
        interactor.fetchImages(topic);
        Mockito.verify(service).getImages(topic);
    }
}