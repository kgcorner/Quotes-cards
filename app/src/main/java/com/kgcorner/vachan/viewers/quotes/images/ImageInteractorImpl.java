package com.kgcorner.vachan.viewers.quotes.images;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.sdk.models.Image;

import java.util.List;

import io.reactivex.Observable;

public class ImageInteractorImpl implements ImageInteractor {

    private final VachanService service;

    public ImageInteractorImpl(VachanService service){

        this.service = service;
    }

    @Override
    public Observable<List<Image>> fetchImages(String topic) {
        return service.getImages(topic);
    }
}
