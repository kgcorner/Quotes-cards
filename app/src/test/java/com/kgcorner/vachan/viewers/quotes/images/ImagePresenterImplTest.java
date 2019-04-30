package com.kgcorner.vachan.viewers.quotes.images;

import com.kgcorner.sdk.models.Image;
import com.kgcorner.vachan.util.SchedulerRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.*;

public class ImagePresenterImplTest {

    private ImagePresenter presenter;

    private ImageInteractor interactor;

    @Rule
    public SchedulerRule rule = new SchedulerRule();

    @Before
    public void setUp() throws Exception {
        interactor = Mockito.mock(ImageInteractor.class);
        presenter = new ImagePresenterImpl(interactor);
    }

    @Test
    public void setView() {
        ImageView view = new ImageChooser();
        presenter.setView(view);
        Object view1 = Whitebox.getInternalState(presenter, "view");
        Assert.assertNotNull("view is null", view1 );
    }

    @Test
    public void fetchImages() {
        String topic= "topic";
        List<Image> images = new ArrayList<>();
        Mockito.when(interactor.fetchImages(topic)).thenReturn(Observable.fromArray(images));
        ImageView view = Mockito.mock(ImageView.class);
        presenter.setView(view);
        presenter.fetchImages(topic);
        Mockito.verify(view).loadImage(images);
    }

    @Test
    public void onLoadFail() throws Exception {
        ImageView view = Mockito.mock(ImageView.class);
        presenter.setView(view);
        RuntimeException e = new RuntimeException();
        Whitebox.invokeMethod(presenter, "onLoadFail", e);
        Mockito.verify(view).showError(e);
    }
}