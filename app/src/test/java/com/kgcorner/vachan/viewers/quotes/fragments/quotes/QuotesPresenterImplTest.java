package com.kgcorner.vachan.viewers.quotes.fragments.quotes;

import android.util.Log;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.io.StoreInteractor;
import com.kgcorner.vachan.util.SchedulerRule;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class QuotesPresenterImplTest {

    private QuotesPresenter presenter;
    private QuotesInteractor interactor;
    private StoreInteractor storeInteractor;


    @Rule
    public SchedulerRule rule = new SchedulerRule();

    @Before
    public void init() {
        interactor = Mockito.mock(QuotesInteractor.class);
        presenter = new QuotesPresenterImpl(interactor);
    }

    @Test
    public void getQuotesSuccess() {
        QuotesView view = Mockito.mock(QuotesView.class);
        presenter.setView(view);
        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote());
        Mockito.when(interactor.getQuotes(1)).thenReturn(Observable.fromArray(quotes));
        presenter.getQuotes(1);
        Mockito.verify(view).loadQuotes(quotes);
    }

    @Test
    public void isViewAttachedWhenViewIsNotAttached() {
        Assert.assertFalse(presenter.isViewAttached());
    }

    @Test
    public void isViewAttachedWhenViewIsAttached() {
        presenter.setView(Mockito.mock(QuotesView.class));
        Assert.assertTrue(presenter.isViewAttached());
    }

    @Test
    public void getQuotesError() throws Exception {
        //Test using whitebox mocking
        PowerMockito.mockStatic(Log.class);
        RuntimeException e = new RuntimeException();
        QuotesView view = Mockito.mock(QuotesView.class);
        presenter.setView(view);
        Whitebox.invokeMethod(presenter, "onQuotesLoadFailure", e);
        Mockito.verify(view).showError(e);

    }

    @Test
    public void setView() {
        presenter.setView(Mockito.mock(QuotesView.class));
        Assert.assertTrue(presenter.isViewAttached());
    }
}