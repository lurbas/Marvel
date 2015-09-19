package com.lucasurbas.marvel.fragment.detail.interactor;

import com.lucasurbas.marvel.architecture.Interactor;
import com.lucasurbas.marvel.fragment.detail.presenter.DetailPresenterForInteractor;
import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailInteractor extends Interactor<DetailPresenterForInteractor> {

    void refreshComicImages(ComicPresenterModel comic);
}
