package com.kgcorner.sdk.models;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImageTest {

    private Image image = null;

    private static final String TEST_SOURCE = "TEST_SOURCE";
    private static final String TEST_PHOTOGRAPHER = "TEST_PHOTOGRAPHER";
    private static final String TEST_PHOTOGRAPHER_URL = "TEST_PHOTOGRAPHER_URLe";
    private static final String TEST_IMAGE_URL = "TEST_IMAGE_URL";

    @Before
    public void setUp() throws Exception {
        image = new Image();
    }

    @Test
    public void setSource() {
        image.setSource(TEST_SOURCE);
        Assert.assertEquals("Source is not matching", TEST_SOURCE, image.getSource());
    }

    @Test
    public void setPhotographer() {
        image.setPhotographer(TEST_PHOTOGRAPHER);
        Assert.assertEquals("Photographer is not matching",
                TEST_PHOTOGRAPHER, image.getPhotographer());
    }

    @Test
    public void setPhotographerUrl() {
        image.setPhotographerUrl(TEST_PHOTOGRAPHER_URL);
        Assert.assertEquals("photographer url is not matching",
                TEST_PHOTOGRAPHER_URL, image.getPhotographerUrl());
    }

    @Test
    public void setImageUrl() {
        image.setImageUrl(TEST_IMAGE_URL);
        Assert.assertEquals("image path is not matching",
                TEST_IMAGE_URL, image.getImageUrl());
    }
}