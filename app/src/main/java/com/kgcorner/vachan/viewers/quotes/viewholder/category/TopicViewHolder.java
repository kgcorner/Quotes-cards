package com.kgcorner.vachan.viewers.quotes.viewholder.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.selectedtopics.CardsOnTopicActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopicViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtCategory)
    TextView txtCategory;

    @BindView(R.id.imgCategory)
    ImageView imgCategory;

    private Unbinder unbinder;

    public TopicViewHolder(@NonNull View itemView) {
        super(itemView);
        this.unbinder = ButterKnife.bind(this, itemView);
    }

    public void populate(Topic topic) {
        this.txtCategory.setText(topic.getName());
        Picasso.with(itemView.getContext()).load(topic.getImagePath()).fit()
                .into(imgCategory);
        imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(topic, v);
            }
        });
        txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(topic, v);
            }
        });
    }

    private void launchActivity(Topic topic, View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CardsOnTopicActivity.TOPIC, topic);
        Intent intent = new Intent(v.getContext(), CardsOnTopicActivity.class);
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }


}
