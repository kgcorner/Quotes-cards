package com.kgcorner.vachan.viewers.favouritecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.BaseApplication;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.quotes.viewholder.quotes.QuoteCardAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.arjsna.swipecardlib.SwipeCardView;

public class FavouriteCardsActivity extends AppCompatActivity {

    @Inject
    FavouriteCardPresenter presenter;

    @BindView(R.id.quotesContainer)
    SwipeCardView quotesContainer;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cards);
        unbinder = ButterKnife.bind(this);
        ((BaseApplication)getApplication()).getAppComponent().inject(this);

        List<Quote> favQuotes = presenter.getFavCards();

        QuoteCardAdapter adapter = new QuoteCardAdapter(this,
                R.layout.quote_card, favQuotes, this);
        setSwipeCardFlingListener();
        quotesContainer.setAdapter(adapter);
        quotesContainer.setMinStackInAdapter(1);
    }


    private void setSwipeCardFlingListener() {
        quotesContainer.setFlingListener(new SwipeCardView.OnCardFlingListener() {
            @Override
            public void onCardExitLeft(Object dataObject) {

            }

            @Override
            public void onCardExitRight(Object dataObject) {

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                Toast.makeText(quotesContainer.getContext(),
                        "This is last card", Toast.LENGTH_SHORT ).show();
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
    protected void onDestroy() {
        super.onDestroy();
        this.unbinder.unbind();
    }
}
