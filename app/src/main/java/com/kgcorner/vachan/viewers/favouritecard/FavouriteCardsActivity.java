package com.kgcorner.vachan.viewers.favouritecard;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.BaseApplication;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.BaseView;
import com.kgcorner.vachan.viewers.quotes.viewholder.quotes.QuoteCardAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.arjsna.swipecardlib.SwipeCardView;

public class FavouriteCardsActivity extends AppCompatActivity implements BaseView {

    @Inject
    FavouriteCardPresenter presenter;

    @BindView(R.id.quotesContainer)
    SwipeCardView quotesContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.noCardLayout)
    View noCardLayout;

    @BindView(R.id.txtNoItem)
    TextView txtNoItem;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cards);
        unbinder = ButterKnife.bind(this);
        ((BaseApplication)getApplication()).getAppComponent().inject(this);

        List<Quote> favQuotes = presenter.getFavCards();
        if(favQuotes == null || favQuotes.isEmpty()) {
            noCardLayout.setVisibility(View.VISIBLE);
            txtNoItem.setText(R.string.no_fav_text);
        } else {
            noCardLayout.setVisibility(View.GONE);
        }


        QuoteCardAdapter adapter = new QuoteCardAdapter(this,
                R.layout.quote_card, favQuotes, this);
        setSwipeCardFlingListener();
        quotesContainer.setAdapter(adapter);
        quotesContainer.setMinStackInAdapter(1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                noCardLayout.setVisibility(View.VISIBLE);
                txtNoItem.setText(R.string.no_more_fav_text);
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

    @Override
    public void showError(Throwable e) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.parent),
                e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }
}
