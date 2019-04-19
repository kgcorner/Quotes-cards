package com.kgcorner.vachan.viewers.quotes.images;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.sdk.models.Image;
import com.kgcorner.vachan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>{

    private final List<Image> images;
    private final com.kgcorner.vachan.viewers.quotes.images.ImageView view;

    public ImageAdapter(List<Image> images, com.kgcorner.vachan.viewers.quotes.images.ImageView view) {

        this.images = images;
        this.view = view;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int option) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image,
                viewGroup, false);
        return new ImageViewHolder(view, this.view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int position) {
        Image image = images.get(position);
        imageViewHolder.populate(image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder {


    private final com.kgcorner.vachan.viewers.quotes.images.ImageView view;
    @BindView(R.id.imgImage)
    ImageView imgImage;

    @BindView(R.id.txtSource)
    TextView txtSource;

    @BindView(R.id.txtPhotographer)
    TextView txtPhotographer;

    private Image image;


    public ImageViewHolder(@NonNull View itemView, com.kgcorner.vachan.viewers.quotes.images.ImageView view) {
        super(itemView);
        this.view = view;
        ButterKnife.bind(this, itemView);
        txtSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image != null && image.getImageUrl() != null
                        && image.getImageUrl().trim().length() > 0) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(image.getImageUrl()));
                    itemView.getContext().startActivity(browserIntent);
                }
            }
        });

        txtPhotographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image != null && image.getPhotographerUrl() != null
                        && image.getPhotographerUrl().trim().length() > 0) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(image.getPhotographerUrl()));
                    itemView.getContext().startActivity(browserIntent);
                }
            }
        });

        imgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.onImageChoose(image);
            }
        });
    }

    public void populate(Image image) {
        this.image = image;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) view).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels > 600? dm.heightPixels - 200 : dm.heightPixels;

        Picasso.with(itemView.getContext()).load(image.getImageUrl())
                .resize(width,height)
                .centerInside()
                .into(imgImage);
        txtPhotographer.setText("Taken by "+image.getPhotographer());
        setUnderLineText(txtPhotographer, image.getPhotographer());
        txtSource.setText(image.getSource());
        setUnderLineText(txtSource, image.getSource());
    }

    public void setUnderLineText(TextView tv, String textToUnderLine) {
        if(textToUnderLine == null || textToUnderLine.trim().length() < 1)
            return;
        String tvt = tv.getText().toString();
        int ofe = tvt.indexOf(textToUnderLine, 0);

        UnderlineSpan underlineSpan = new UnderlineSpan();
        SpannableString wordToSpan = new SpannableString(tv.getText());
        for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
            ofe = tvt.indexOf(textToUnderLine, ofs);
            if (ofe == -1)
                break;
            else {
                wordToSpan.setSpan(underlineSpan, ofe, ofe + textToUnderLine.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }


}