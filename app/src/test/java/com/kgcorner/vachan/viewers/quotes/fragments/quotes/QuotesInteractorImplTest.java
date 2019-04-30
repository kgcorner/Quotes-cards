package com.kgcorner.vachan.viewers.quotes.fragments.quotes;

import com.kgcorner.sdk.VachanService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class QuotesInteractorImplTest {

    private QuotesInteractor interactor;
    private VachanService service;

    @Before
    public void setUp() throws Exception {
        service = Mockito.mock(VachanService.class);
        interactor = new QuotesInteractorImpl(service);
    }

    @Test
    public void getQuotes() {
        interactor.getQuotes(1);
        Mockito.verify(service).getQuotes(1);
    }
}