package com.lucasurbas.marvel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 19/09/15.
 */
public class ComicPresenterModel implements Parcelable {

    private long id;

    private String title;

    private String description;

    private String thumbnailUrl;

    private List<String> imageUrlList;

    public ComicPresenterModel(Comic comic) {
        this.id = comic.getId();
        this.title = comic.getTitle();
        this.description = comic.getDescription();
        this.thumbnailUrl = comic.getThumbnail().getThumbnailUrl();
        imageUrlList = new ArrayList<>();
        for (Image i : comic.getImages()) {
            imageUrlList.add(i.getDetailUrl());
        }
    }

    protected ComicPresenterModel(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<ComicPresenterModel> CREATOR = new Creator<ComicPresenterModel>() {
        @Override
        public ComicPresenterModel createFromParcel(Parcel in) {
            return new ComicPresenterModel(in);
        }

        @Override
        public ComicPresenterModel[] newArray(int size) {
            return new ComicPresenterModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeLong(id);
        out.writeString(title);
        out.writeString(description);
        out.writeString(thumbnailUrl);
        out.writeStringList(imageUrlList);
    }

    private void readFromParcel(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        thumbnailUrl = in.readString();
        imageUrlList = new ArrayList<>();
        in.readStringList(imageUrlList);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
}
