package com.kgcorner.vachan.viewers.quotes.fragments.topics;

import com.kgcorner.sdk.models.Topic;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopicPresenterImpl implements TopicsPresenter {

    private final TopicInteractor interactor;
    private TopicsView view;

    public TopicPresenterImpl(TopicInteractor interactor) {

        this.interactor = interactor;
    }

    public void setView(TopicsView view) {
        this.view = view;
    }

    @Override
    public void fetchTopics() {
        interactor.fetchTopics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTopicLoadSuccess, this::onTopicLoadFailure);
    }

    private void onTopicLoadFailure(Throwable throwable) {
        view.showError(throwable);
    }

    private void onTopicLoadSuccess(List<Topic> topics) {
        view.loadTopics(topics);
    }
}
