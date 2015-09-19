package com.lucasurbas.marvel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lucas on 18/09/15.
 */
public class ComicsResponse {

    @Expose
    @SerializedName("data")
    private Data data;

    public List<Comic> getComics() {
        return data.getComicList();
    }
}
