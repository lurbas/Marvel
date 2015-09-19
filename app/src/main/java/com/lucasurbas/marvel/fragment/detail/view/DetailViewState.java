package com.lucasurbas.marvel.fragment.detail.view;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailViewState implements RestorableViewState<DetailView> {

    private final String KEY_COMIC = "key_comic";
    private final String KEY_CURRENT_IMAGE_URL = "key_current_image_url";

    private ComicPresenterModel comic;
    private String currentImageUrl;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putString(KEY_CURRENT_IMAGE_URL, currentImageUrl);
        out.putParcelable(KEY_COMIC, comic);
    }

    @Override
    public RestorableViewState<DetailView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        this.currentImageUrl = in.getString(KEY_CURRENT_IMAGE_URL);
        this.comic = in.getParcelable(KEY_COMIC);
        return this;
    }

    @Override
    public void apply(DetailView view, boolean b) {

        view.setComicData(comic);
        if (currentImageUrl != null) {
            view.setImage(currentImageUrl);
        }
    }

    public void setComic(ComicPresenterModel comic) {
        this.comic = comic;
    }

    public void setCurrentImageUrl(String currentImageUrl) {
        this.currentImageUrl = currentImageUrl;
    }


}
