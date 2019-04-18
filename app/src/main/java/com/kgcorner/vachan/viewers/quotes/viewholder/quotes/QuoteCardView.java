package com.kgcorner.vachan.viewers.quotes.viewholder.quotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuoteCardView extends View {

    private Quote quote;

    @BindView(R.id.txtQuote)
    TextView txtQuote;

    @BindView(R.id.txtAuthor)
    TextView txtAuthor;

    @BindView(R.id.imgLike)
    ImageView imgLike;

    @BindView(R.id.imgShare)
    ImageView imgShare;

    private Unbinder unbinder;

    public QuoteCardView(Context context, @NonNull View itemView) {
        super(context);
        this.unbinder = ButterKnife.bind(this, itemView);
    }


    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void populate() {
        txtQuote.setText(quote.getQuote());
        txtAuthor.setText(quote.getAuthor());
    }
}
