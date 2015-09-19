package com.lucasurbas.marvel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.lucasurbas.marvel.constant.Field;

/**
 * Created by Lucas on 19/09/15.
 */
@DatabaseTable
public class Image {

    private static final String THUMBNAIL_VARIANT = "/standard_fantastic.";
    private static final String DETAIL_VARIANT = "/detail.";

    @DatabaseField(generatedId = true, columnName = Field.ID)
    private long id;

    @DatabaseField()
    @Expose
    @SerializedName("path")
    private String url;

    @DatabaseField()
    @Expose
    @SerializedName("extension")
    private String extension;

    @DatabaseField(foreign = true)
    private Comic comic;

    public String getThumbnailUrl() {
        return url + THUMBNAIL_VARIANT + extension;
    }

    public String getDetailUrl() {
        return url + DETAIL_VARIANT + extension;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}
