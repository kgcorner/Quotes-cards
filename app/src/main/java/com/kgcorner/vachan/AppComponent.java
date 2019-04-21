package com.kgcorner.vachan;

import com.kgcorner.vachan.io.StoreModule;
import com.kgcorner.vachan.services.ServicesModule;
import com.kgcorner.vachan.viewers.favouritecard.FavouriteCardsActivity;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesListFragment;
import com.kgcorner.vachan.viewers.QuotesViewerModule;
import com.kgcorner.vachan.viewers.quotes.fragments.topics.TopicViewerModule;
import com.kgcorner.vachan.viewers.quotes.fragments.topics.TopicsFragment;
import com.kgcorner.vachan.viewers.quotes.images.ImageChooser;
import com.kgcorner.vachan.viewers.quotes.viewholder.quotes.QuoteCardAdapter;
import com.kgcorner.vachan.viewers.selectedtopics.CardsOnTopicActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        QuotesViewerModule.class,
        TopicViewerModule.class,
        ServicesModule.class,
        StoreModule.class
})
public interface AppComponent {
    void inject(QuotesListFragment quotesListFragment);
    void inject(FavouriteCardsActivity favouriteCardsActivity);
    void inject(TopicsFragment topicsFragment);
    void inject(QuoteCardAdapter quoteCardAdapter);
    void inject(ImageChooser imageChooser);

    void inject(CardsOnTopicActivity cardsOnTopicActivity);
}
