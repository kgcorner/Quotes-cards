package com.kgcorner.sdk.models;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class TopicTest {
    private Topic topic;
    private static final String TEST_NAME = "test name";
    private static final int TEST_ID = 0;
    private static final String TEST_IMAGE_PATH = "http://sample.path.com";
    private static final String[] TAGS = {"tag1","tag2"};

    @Before
    public void init() {
        topic = new Topic();
    }

    @Test
    public void nameTest() {
        topic.setName(TEST_NAME);
        Assert.assertEquals("Name is not matching", TEST_NAME, topic.getName());
    }

    @Test
    public void IdTest() {
        topic.setId(TEST_ID);
        Assert.assertEquals("ID is not matching", TEST_ID, topic.getId());
    }

    @Test
    public void imagePathTest() {
        topic.setImagePath(TEST_IMAGE_PATH);
        Assert.assertEquals("image path is not matching",
                TEST_IMAGE_PATH, topic.getImagePath());
    }

    @Test
    public void tagsTest() {
        topic.setTags(TAGS);
        Assert.assertEquals("tags count is not matching",
                TAGS.length, topic.getTags().length);
        Assert.assertEquals("Tag is not matching", TAGS[0], topic.getTags()[0]);
        Assert.assertEquals("tag is not matching", TAGS[1], topic.getTags()[1]);
    }
}
