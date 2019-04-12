package com.kgcorner.vachan;

import com.kgcorner.vachan.services.ServicesModule;
import com.kgcorner.vachan.viewers.listview.fragments.quotes.QuotesListFragment;
import com.kgcorner.vachan.viewers.listview.fragments.quotes.QuotesViewerModule;
import com.kgcorner.vachan.viewers.listview.fragments.topics.TopicViewerModule;
import com.kgcorner.vachan.viewers.listview.fragments.topics.TopicsFragment;

import dagger.Component;

@Component(modules = {
        QuotesViewerModule.class,
        TopicViewerModule.class,
        ServicesModule.class
})
public interface AppComponent {
    void inject(QuotesListFragment quotesListFragment);
    void inject(TopicsFragment topicsFragment);
}
