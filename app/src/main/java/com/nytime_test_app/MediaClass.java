package com.nytime_test_app;

import java.io.Serializable;
import java.util.ArrayList;

public class MediaClass implements Serializable {
    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    private int approved_for_syndication;
    private ArrayList<MediaMetadataClass> mediaMetadataArrayList;

    public MediaClass(String type, String subtype, String caption, String copyright, int approved_for_syndication, ArrayList<MediaMetadataClass> mediaMetadataArrayList) {
        this.type = type;
        this.subtype = subtype;
        this.caption = caption;
        this.copyright = copyright;
        this.approved_for_syndication = approved_for_syndication;
        this.mediaMetadataArrayList = mediaMetadataArrayList;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getApproved_for_syndication() {
        return approved_for_syndication;
    }

    public ArrayList<MediaMetadataClass> getMediaMetadataArrayList() {
        return mediaMetadataArrayList;
    }

}
