package com.kgcorner.vachan.viewers.quotes.viewholder.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.R;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicViewHolder> {

    private List<Topic> categories;

    public TopicsAdapter(List<Topic> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.topic_card, viewGroup, false);
        TopicViewHolder categoryViewHolder = new TopicViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder viewHolder, int i) {
        Topic topic = this.categories.get(i);
        viewHolder.populate(topic);
    }

    @Override
    public int getItemCount() {
        return this.categories == null ? 0: this.categories.size();
    }
}
