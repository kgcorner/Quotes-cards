package com.kgcorner.sdk.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class QuoteTest {

    private Quote quote = null;
    private static final String TEST_QUOTE = "TEST_QUOTE";
    private static final String TEST_AUTHOR = "TEST_AUTHOR";
    private static final int TEST_ID = 1;


    @Before
    public void setUp() throws Exception {
        quote = new Quote();
    }

    @Test
    public void setQuote() {
        quote.setQuote(TEST_QUOTE);
        Assert.assertEquals("Quote is not matching", TEST_QUOTE, quote.getQuote());
    }

    @Test
    public void setAuthor() {
        quote.setAuthor(TEST_AUTHOR);
        Assert.assertEquals("Author is not matching", TEST_AUTHOR, quote.getAuthor());
    }

    @Test
    public void setDateAdded() {
        Date date = new Date();
        quote.setDateAdded(date);
        Assert.assertEquals("Date added is not matching", date, quote.getDateAdded());
    }

    @Test
    public void setId() {
        quote.setId(TEST_ID);
        Assert.assertEquals("ID is not matching", TEST_ID, quote.getId());
    }

    @Test
    public void equals() {
        Quote secondQUote = new Quote();
        secondQUote.setId(TEST_ID);
        quote.setId(TEST_ID);
        Assert.assertTrue("Quotes are not equal", quote.equals(secondQUote));
    }

    @Test
    public void equalsFalse() {
        quote.setId(TEST_ID);
        Assert.assertFalse("Quotes are equal", quote.equals(new Object()));
    }

    @Test
    public void equalsUnMatchingId() {
        Quote secondQUote = new Quote();
        secondQUote.setId(TEST_ID+1);
        quote.setId(TEST_ID);
        Assert.assertFalse("Quotes are equal", quote.equals(secondQUote));
    }

    @Test
    public void setTags() {
        quote.setTags(Arrays.asList("tag1", "tag2"));
        Assert.assertEquals("tag count is not matching",
                2, quote.getTags().size());
    }
}