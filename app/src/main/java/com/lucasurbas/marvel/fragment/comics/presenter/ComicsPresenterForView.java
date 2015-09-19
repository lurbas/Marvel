package com.lucasurbas.marvel.fragment.comics.presenter;

import com.lucasurbas.marvel.architecture.PresenterForView;
import com.lucasurbas.marvel.fragment.comics.view.ComicsView;

/**
 * Created by Lucas on 29/08/15.
 */
public interface ComicsPresenterForView extends PresenterForView<ComicsView> {

    void loadComics();

    void loadMoreComics();
}
