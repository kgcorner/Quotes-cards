package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import com.kgcorner.sdk.VachanService;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class QuotesViewerModuleTest {

    @Test
    public void provideQuotesInetractor() {
        Assert.assertNotNull(QuotesViewerModule.
                provideQuotesInetractor(Mockito.mock(VachanService.class)));
    }

    @Test
    public void providesQuotesPresenter() {
        Assert.assertNotNull(QuotesViewerModule.
                providesQuotesPresenter(Mockito.mock(QuotesInteractor.class)));
    }
}