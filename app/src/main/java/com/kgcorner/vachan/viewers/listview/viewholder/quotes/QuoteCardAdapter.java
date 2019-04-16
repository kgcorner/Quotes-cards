package com.kgcorner.vachan.viewers.listview.viewholder.quotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.io.Store;

import java.util.List;

public class QuoteCardAdapter extends ArrayAdapter<Quote> {

    private final List<Quote> quotes;
    private final Context context;
    private final int resource;
    Store store = Store.getInstance();
    private static class ViewHolder {
        TextView txtQuote;
        TextView txtAuthor;
        ImageView imgLike;
        ImageView imgShare;
    }

    public QuoteCardAdapter(@NonNull Context context, int resource,
                            @NonNull List<Quote> objects) {
        super(context, resource, objects);
        this.context = context;
            this.resource = resource;
            this.quotes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        Quote quote = getItem(position);
        if(quote == null)
            return null;
        if(convertView == null) {

            Context context = parent.getContext();
            convertView = LayoutInflater.from(context).inflate(R.layout.quote_card, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.txtAuthor = convertView.findViewById(R.id.txtAuthor);
            viewHolder.txtQuote =  convertView.findViewById(R.id.txtQuote);
            viewHolder.imgLike = convertView.findViewById(R.id.imgLike);
            if(store.getFavQuotes().contains(quote)) {
                viewHolder.imgLike.setImageDrawable(getContext()
                        .getDrawable(R.drawable.likefilled));
                viewHolder.imgLike.setTag(R.drawable.likefilled);
            } else {
                viewHolder.imgLike.setImageDrawable(getContext()
                        .getDrawable(R.drawable.like));
                viewHolder.imgLike.setTag(R.drawable.like);
            }
            setLikeClickListener(viewHolder.imgLike, quote);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtAuthor.setText(quote.getAuthor());
        viewHolder.txtQuote.setText(quote.getQuote());
        /*QuoteCardView view = new QuoteCardView(context, convertView);
        view.setQuote(getItem(position));
        view.populate();*/
        return convertView;
    }

    private void setLikeClickListener (ImageView imgLike, Quote quote) {
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img = (ImageView) v;

                if(img.getTag() == null || (int) img.getTag() == R.drawable.like ) {
                    img.setTag(R.drawable.likefilled);
                    img.setImageDrawable(getContext().getDrawable(R.drawable.likefilled));
                    if(store != null) {
                        store.addToFav(quote);
                    }
                } else {
                    if((int) img.getTag() == R.drawable.likefilled) {
                        img.setTag(R.drawable.like);
                        img.setImageDrawable(getContext().getDrawable(R.drawable.like));
                        if(store != null) {
                            store.removeFromFav(quote);
                        }
                    }
                }


            }
        });
    }

    @Override
    public int getCount() {
        return this.quotes != null ? this.quotes.size(): 0;
    }

    @Nullable
    @Override
    public Quote getItem(int position) {
        if(getCount() > position)
            return super.getItem(position);
        else
            return null;
    }
}
