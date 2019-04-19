package com.kgcorner.vachan.viewers.quotes.images;

import com.kgcorner.sdk.models.Image;

import java.util.List;

import io.reactivex.Observable;

public interface ImageInteractor {
    Observable<List<Image>> fetchImages(String topic);
}
