package com.kgcorner.sdk.models;

import java.util.Date;
import java.util.List;

/**
 * Represents a quote
 */
public class Quote {

    private String quote;
    private String author;
    private Date dateAdded;

    /**
     * returns the quote itself
     * @return
     */
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    /**
     * Returns name of the author of the quote
     * @return
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the date quote was added to the collection
     * @return
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

}
