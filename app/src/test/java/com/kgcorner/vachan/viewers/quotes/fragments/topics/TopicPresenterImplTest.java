package com.kgcorner.vachan.viewers.quotes.fragments.topics;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.util.SchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class TopicPresenterImplTest {

    private TopicsPresenter presenter;
    private TopicInteractor interactor;

    @Rule
    public SchedulerRule rule = new SchedulerRule();

    @Before
    public void init() {
        interactor = Mockito.mock(TopicInteractor.class);
        presenter = new TopicPresenterImpl(interactor);
    }

    @Test
    public void fetchTopics() {
        List<Topic> topics = new ArrayList<>();
        Mockito.when(interactor.fetchTopics()).thenReturn(Observable.fromArray(topics));
        TopicsView view = Mockito.mock(TopicsView.class);
        presenter.setView(view);
        presenter.fetchTopics();
        Mockito.verify(view).loadTopics(topics);
    }

    @Test
    public void onTopicLoadFailure() throws Exception {
        TopicsView view = Mockito.mock(TopicsView.class);
        presenter.setView(view);
        RuntimeException e = new RuntimeException();
        Whitebox.invokeMethod(presenter, "onTopicLoadFailure", e);
        Mockito.verify(view).showError(e);
    }
}