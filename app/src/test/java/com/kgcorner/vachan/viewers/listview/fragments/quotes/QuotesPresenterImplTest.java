package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.util.SchedulerRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class QuotesPresenterImplTest {

    private QuotesPresenter presenter;
    private QuotesInteractor interactor;


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
        presenter.getQuotes();
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
    public void setView() {
        presenter.setView(Mockito.mock(QuotesView.class));
        Assert.assertTrue(presenter.isViewAttached());
    }
}