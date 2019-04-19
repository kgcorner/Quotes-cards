package com.kgcorner.sdk.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents a quote
 */
public class Quote implements Serializable {

    private String quote;
    private String author;
    private Date dateAdded;
    private int id;
    private List<String> tags;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Quote)) {
            return false;
        }
        Quote quote = (Quote) obj;
        return id == quote.id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
