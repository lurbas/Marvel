package com.lucasurbas.marvel.fragment.comics.presenter;

import com.lucasurbas.marvel.architecture.PresenterForInteractor;
import com.lucasurbas.marvel.fragment.comics.interactor.ComicsInteractor;
import com.lucasurbas.marvel.model.ComicPresenterModel;

import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public interface ComicsPresenterForInteractor extends PresenterForInteractor<ComicsInteractor> {

    void showComics(boolean success, List<ComicPresenterModel> comicList, int offset);
}
