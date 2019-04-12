package com.kgcorner.vachan.viewers.listview.viewholder.quotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.R;

import java.util.List;

public class QuotesRecycleViewAdapter extends RecyclerView.Adapter<QuoteCardRecyclerViewHolder> {

    private final List<Quote> quotes;

    public QuotesRecycleViewAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }
    @NonNull
    @Override
    public QuoteCardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.quote_card, viewGroup,
                false);

        return new QuoteCardRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteCardRecyclerViewHolder quoteCard, int i) {
        Quote quote = quotes.get(i);
        quoteCard.setQuote(quote);
        quoteCard.populate();
    }

    @Override
    public int getItemCount() {
        int count = this.quotes == null? 0: this.quotes.size();
        return count;
    }
}
