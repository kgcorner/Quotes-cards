package com.kgcorner.vachan.viewers;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.viewers.QuotesViewerModule;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesInteractor;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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