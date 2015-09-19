package com.lucasurbas.marvel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lucas on 19/09/15.
 */
public class Data {

    @Expose
    @SerializedName("offset")
    private int offset;

    @Expose
    @SerializedName("limit")
    private int limit;

    @Expose
    @SerializedName("total")
    private int total;

    @Expose
    @SerializedName("count")
    private int count;

    @Expose
    @SerializedName("results")
    private List<Comic> comicList;

    public List<Comic> getComicList() {
        return comicList;
    }
}
