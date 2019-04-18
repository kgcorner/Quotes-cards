package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QuoteRealMObject extends RealmObject {

    @PrimaryKey
    private int id;
    private String quote;
    private String author;

    public static final String ID = "id";

    public QuoteRealMObject(Quote quote) {
        id = quote.getId();
        this.quote = quote.getQuote();
        this.author = quote.getAuthor();

    }

    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public Quote readlMToQuote() {
        Quote quote = new Quote();
        quote.setId(id);
        quote.setQuote(this.quote);
        quote.setAuthor(this.getAuthor());
        return quote;
    }

    /**
     * Mandatory no agr constructor
     */
    public QuoteRealMObject(){}
}
