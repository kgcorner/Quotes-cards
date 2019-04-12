package com.kgcorner.vachan.viewers.listview.fragments.topics;

import com.kgcorner.sdk.VachanService;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TopicViewerModule {

    @Provides
    public static TopicInteractor providesTopicInteractor(VachanService service) {
        return new TopicInteractorImpl(service);
    }

    @Provides
    public static TopicsPresenter providesTopicsPresenter(TopicInteractor interactor) {
        return new TopicPresenterImpl(interactor);
    }
}
