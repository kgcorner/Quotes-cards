package com.kgcorner.vachan.viewers.selectedtopics;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.BaseApplication;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.quotes.viewholder.quotes.QuoteCardAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.arjsna.swipecardlib.SwipeCardView;

public class CardsOnTopicActivity extends AppCompatActivity implements CardOnTopicView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.cardsContainer)
    SwipeCardView cardContainer;

    @BindView(R.id.imgLoader)
    ImageView imgLoader;
    private Unbinder unbinder;

    private List<Quote> quotes = new ArrayList<>();

    public static final  String TOPIC = "topic";

    @Inject
    CardOnTopicPresenter presenter;
    String topics = "";
    private QuoteCardAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_on_topic);
        unbinder = ButterKnife.bind(this);
        adapter = new QuoteCardAdapter(this, R.layout.quote_card,
                quotes, this);
        cardContainer.setAdapter(adapter);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);

        Topic topic = (Topic)  getIntent().getExtras().getSerializable(TOPIC);

        if(topic == null) {
            finish();
            return;
        }

        for(String t : topic.getTags()) {
            topics+=(t+",");
        }

        if(topics.trim().length() >1) {
            topics = topics.substring(0, topics.length() -1);
        }
        setSupportActionBar(toolbar);
        String title = topic.getName().substring(0,1).toUpperCase()+topic.getName().substring(1);
        toolbar.setTitle(title);
        showLoader();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int minCardExpected = 1;
        cardContainer.setMinStackInAdapter(minCardExpected);
        presenter.setView(this);
        cardContainer.setFlingListener(new SwipeCardView.OnCardFlingListener() {
            @Override
            public void onCardExitLeft(Object dataObject) {

            }

            @Override
            public void onCardExitRight(Object dataObject) {

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                presenter.fetchQuotes(topics);
                String toastText = getBaseContext().getString(R.string.get_more_quote);
                Toast.makeText(getBaseContext(), toastText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }

            @Override
            public void onCardExitTop(Object dataObject) {

            }

            @Override
            public void onCardExitBottom(Object dataObject) {

            }
        });
    }

    @Override
    public void loadQuotes(List<Quote> quotes) {
        this.quotes.clear();
        this.quotes.addAll(quotes);
        adapter.notifyDataSetChanged();
        hideLoader();
    }

    @Override
    public void showError(Throwable e) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.parent),
                e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showLoader() {
        imgLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        imgLoader.setVisibility(View.GONE);
    }
}
