package com.kgcorner.vachan.viewers.listview.viewholder.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.R;
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
    }


}
