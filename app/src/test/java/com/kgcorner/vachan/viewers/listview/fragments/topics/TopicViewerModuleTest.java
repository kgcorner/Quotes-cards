package com.kgcorner.vachan.viewers.listview.fragments.topics;

import com.kgcorner.sdk.VachanService;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TopicViewerModuleTest {

    @Test
    public void providesTopicInteractor() {
        Assert.assertNotNull(TopicViewerModule
                .providesTopicInteractor(Mockito.mock(VachanService.class)));
    }

    @Test
    public void providesTopicsPresenter() {
        Assert.assertNotNull(TopicViewerModule
                .providesTopicsPresenter(Mockito.mock(TopicInteractor.class)));
    }
}