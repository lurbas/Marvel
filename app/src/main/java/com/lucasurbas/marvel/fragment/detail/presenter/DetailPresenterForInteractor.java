package com.lucasurbas.marvel.fragment.detail.presenter;


import com.lucasurbas.marvel.architecture.PresenterForInteractor;
import com.lucasurbas.marvel.fragment.detail.interactor.DetailInteractor;
import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailPresenterForInteractor extends PresenterForInteractor<DetailInteractor> {

    void setComicImages(ComicPresenterModel comic, boolean success);
}
