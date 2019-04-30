package com.kgcorner.vachan.viewers.selectedtopics;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.util.SchedulerRule;
import com.kgcorner.vachan.viewers.quotes.fragments.topics.TopicsView;
import com.kgcorner.vachan.viewers.quotes.images.ImageChooser;
import com.kgcorner.vachan.viewers.quotes.images.ImageView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.*;

public class CardOnTopicPresenterImplTest {

    private CardOnTopicPresenter presenter;
    private CardOnTopicInteractor interactor;

    @Rule
    public SchedulerRule rule = new SchedulerRule();


    @Before
    public void setUp() throws Exception {
        interactor = Mockito.mock(CardOnTopicInteractor.class);
        presenter = new CardOnTopicPresenterImpl(interactor);
    }

    @Test
    public void fetchQuotes() {
        String topic = "topic";
        int page = 1;
        List<Quote> quotes = new ArrayList<>();
        Mockito.when(interactor.getQuotesOnTopics(topic, page)).thenReturn(Observable.fromArray(quotes));
        CardOnTopicView view = Mockito.mock(CardOnTopicView.class);
        presenter.setView(view);
        presenter.fetchQuotes(topic);
        Mockito.verify(view).loadQuotes(quotes);

    }

    @Test
    public void setView() {
        CardOnTopicView view = new CardsOnTopicActivity();
        presenter.setView(view);
        Object view1 = Whitebox.getInternalState(presenter, "view");
        Assert.assertNotNull("view is null", view1 );
    }
}