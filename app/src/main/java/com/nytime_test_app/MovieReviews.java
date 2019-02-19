package com.nytime_test_app;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieReviews implements Serializable {

    private String url;
    private String adx_keywords;
    private String column;
    private String section;
    private String byline;
    private String type;
    private String title;
    private String abstractString;
    private String published_date;
    private String source;
    private int id;
    private int asset_id;
    private int views;
    private ArrayList<String> des_facetArray;
    private ArrayList<String> org_facetArray;
    private ArrayList<String> per_facetArray;
    private ArrayList<String> geo_facetArray;
    private ArrayList<MediaClass> mediaArrayList;

    public MovieReviews(String url, String adx_keywords, String column, String section, String byline, String type, String title, String abstractString, String published_date, String source, int id, int asset_id, int views, ArrayList<String> des_facetArray, ArrayList<String> org_facetArray, ArrayList<String> per_facetArray, ArrayList<String> geo_facetArray, ArrayList<MediaClass> mediaArrayList) {
        this.url = url;
        this.adx_keywords = adx_keywords;
        this.column = column;
        this.section = section;
        this.byline = byline;
        this.type = type;
        this.title = title;
        this.abstractString = abstractString;
        this.published_date = published_date;
        this.source = source;
        this.id = id;
        this.asset_id = asset_id;
        this.views = views;
        this.des_facetArray = des_facetArray;
        this.org_facetArray = org_facetArray;
        this.per_facetArray = per_facetArray;
        this.geo_facetArray = geo_facetArray;
        this.mediaArrayList = mediaArrayList;
    }

    public String getUrl() {
        return url;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public String getColumn() {
        return column;
    }

    public String getSection() {
        return section;
    }

    public String getByline() {
        return byline;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractString() {
        return abstractString;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getSource() {
        return source;
    }

    public int getId() {
        return id;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public int getViews() {
        return views;
    }

    public ArrayList<String> getDes_facetArray() {
        return des_facetArray;
    }

    public ArrayList<String> getOrg_facetArray() {
        return org_facetArray;
    }

    public ArrayList<String> getPer_facetArray() {
        return per_facetArray;
    }

    public ArrayList<String> getGeo_facetArray() {
        return geo_facetArray;
    }

    public ArrayList<MediaClass> getMediaArrayList() {
        return mediaArrayList;
    }

}
