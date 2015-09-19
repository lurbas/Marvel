package com.lucasurbas.marvel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.lucasurbas.marvel.constant.Field;

import java.util.Collection;

/**
 * Created by Lucas on 18/09/15.
 */
@DatabaseTable
public class Comic {

    @DatabaseField(id = true, columnName = Field.ID)
    @Expose
    @SerializedName("id")
    private long id;

    @DatabaseField()
    @Expose
    @SerializedName("title")
    private String title;

    @DatabaseField()
    @Expose
    @SerializedName("description")
    private String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    @Expose
    @SerializedName("thumbnail")
    private Image thumbnail;

    @ForeignCollectionField
    @Expose
    @SerializedName("images")
    private Collection<Image> images;

    @DatabaseField(columnName = Field.ORDER)
    private int order;

    public Comic() {
        // for ORMLite
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public Collection<Image> getImages() {
        return images;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
