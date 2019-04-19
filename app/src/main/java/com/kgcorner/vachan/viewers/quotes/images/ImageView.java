package com.kgcorner.vachan.viewers.quotes.images;

import com.kgcorner.sdk.models.Image;
import com.kgcorner.vachan.viewers.BaseView;

import java.util.List;

public interface ImageView extends BaseView {
    void loadImage(List<Image> images);
    void onImageChoose(Image image);
}
