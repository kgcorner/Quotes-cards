package com.kgcorner.vachan.viewers;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.io.StoreInteractor;
import com.kgcorner.vachan.viewers.QuotesViewerModule;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesInteractor;
import com.kgcorner.vachan.viewers.quotes.images.ImageInteractor;
import com.kgcorner.vachan.viewers.selectedtopics.CardOnTopicInteractor;

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

    @Test
    public void providesFavQuotesPresenter() {
        Assert.assertNotNull(QuotesViewerModule.
                providesFavQuotesPresenter(Mockito.mock(StoreInteractor.class)));
    }

    @Test
    public void provideImageInteractor() {
        Assert.assertNotNull(QuotesViewerModule.
                provideImageInteractor(Mockito.mock(VachanService.class)));
    }

    @Test
    public void provideImagePresenter() {
        Assert.assertNotNull(QuotesViewerModule.
                provideImagePresenter(Mockito.mock(ImageInteractor.class)));
    }

    @Test
    public void providesInteractor() {
        Assert.assertNotNull(QuotesViewerModule.
                providesInteractor(Mockito.mock(VachanService.class)));
    }

    @Test
    public void providesCardOnTopic() {
        Assert.assertNotNull(QuotesViewerModule.
                providesCardOnTopic(Mockito.mock(CardOnTopicInteractor.class)));
    }
}