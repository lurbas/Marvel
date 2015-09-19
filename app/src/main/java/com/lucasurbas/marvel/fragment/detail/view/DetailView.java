package com.lucasurbas.marvel.fragment.detail.view;


import com.lucasurbas.marvel.architecture.View;
import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailView extends View {

    void setComicData(ComicPresenterModel comic);

    void setImage(String imageUrl);

}
