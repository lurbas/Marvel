package com.lucasurbas.marvel.fragment.detail.presenter;


import com.lucasurbas.marvel.architecture.PresenterForView;
import com.lucasurbas.marvel.fragment.detail.view.DetailView;
import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailPresenterForView extends PresenterForView<DetailView> {

    void initComic(ComicPresenterModel comic);

    void shuffleImage();
}
