package com.nytime_test_app;

import java.io.Serializable;

public class MediaMetadataClass implements Serializable {
    private String url;
    private String format;
    private int height;
    private int width;

    public MediaMetadataClass(String url, String format, int height, int width) {
        this.url = url;
        this.format = format;
        this.height = height;
        this.width = width;
    }


    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
