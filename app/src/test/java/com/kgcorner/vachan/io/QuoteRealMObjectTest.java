package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class QuoteRealMObjectTest {

    private QuoteRealMObject realmObject;
    private Quote quote = null;
    @Before
    public void setUp() throws Exception {
        quote = new Quote();
        quote.setId(1);
        quote.setTags(Arrays.asList("tag1", "tag2"));
        quote.setDateAdded(new Date());
        quote.setAuthor("Test Author");
        quote.setQuote("Test quote");
        realmObject = new QuoteRealMObject(quote);
    }

    @Test
    public void getId() {
        Assert.assertEquals("id is not matching", quote.getId(), realmObject.getId());
    }

    @Test
    public void getQuote() {
        Assert.assertEquals("Quote test is not matching",
                quote.getQuote(), realmObject.getQuote());
    }

    @Test
    public void getAuthor() {
        Assert.assertEquals("Author is not matching",
                quote.getAuthor(), realmObject.getAuthor());
    }

    @Test
    public void realMToQuote() {
        Assert.assertEquals("Quote object is not matching",
                quote, realmObject.realMToQuote());
    }
}