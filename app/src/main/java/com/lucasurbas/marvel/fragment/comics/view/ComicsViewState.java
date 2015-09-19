package com.lucasurbas.marvel.fragment.comics.view;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.lucasurbas.marvel.model.ComicPresenterModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public class ComicsViewState implements RestorableViewState<ComicsView> {

    private final String KEY_COMICS = "key_comics";
    private final String KEY_LOADING = "key_loading";
    private final String KEY_INFO_TEXT = "key_info_text";

    private ArrayList<ComicPresenterModel> comicList;
    private boolean isLoading;
    private String infoText;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putBoolean(KEY_LOADING, isLoading);
        out.putParcelableArrayList(KEY_COMICS, comicList);
        out.putString(KEY_INFO_TEXT, infoText);
    }

    @Override
    public RestorableViewState<ComicsView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        this.isLoading = in.getBoolean(KEY_LOADING, false);
        this.comicList = in.getParcelableArrayList(KEY_COMICS);
        this.infoText = in.getString(KEY_INFO_TEXT);
        return this;
    }

    @Override
    public void apply(ComicsView view, boolean b) {
        view.showProgressIndicator(isLoading);
        view.showComicList(comicList);
        view.setInfoText(infoText);
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setComicList(List<ComicPresenterModel> comicList) {
        if(comicList != null) {
            this.comicList = new ArrayList<>(comicList);
        }
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
}
