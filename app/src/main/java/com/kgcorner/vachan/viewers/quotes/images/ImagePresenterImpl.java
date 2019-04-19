package com.kgcorner.vachan.viewers.quotes.images;

import com.kgcorner.sdk.models.Image;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImagePresenterImpl implements ImagePresenter {

    private final ImageInteractor interactor;
    private ImageView view;

    public ImagePresenterImpl(ImageInteractor interactor) {
        this.interactor = interactor;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    @Override
    public void fetchImages(String topic) {
        interactor.fetchImages(topic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadImages, this:: onLoadFail);
    }

    private void onLoadFail(Throwable throwable) {
        view.showError(throwable);
    }

    private void loadImages(List<Image> images) {
        if(view != null)
            view.loadImage(images);
    }
}
