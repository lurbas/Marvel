package com.lucasurbas.marvel.fragment.comics.interactor;

import com.lucasurbas.marvel.architecture.Interactor;
import com.lucasurbas.marvel.fragment.comics.presenter.ComicsPresenterForInteractor;

/**
 * Created by Lucas on 29/08/15.
 */
public interface ComicsInteractor extends Interactor<ComicsPresenterForInteractor> {

    void loadComics(int offset, int limit);
}
