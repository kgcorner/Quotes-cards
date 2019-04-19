package com.kgcorner.sdk.models;


import java.io.Serializable;

/**
 * Represents an image received from Vachan service
 */
public class Image implements Serializable {
    private String source;
    private String photographer;
    private String photographerUrl;
    private String imageUrl;

    /**
     * Returns source API which provides image info to vachan service
     * @return
     */
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotographerUrl() {
        return photographerUrl;
    }

    public void setPhotographerUrl(String photographerUrl) {
        this.photographerUrl = photographerUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
