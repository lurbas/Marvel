package com.lucasurbas.marvel.fragment.comics.view;

import com.lucasurbas.marvel.architecture.View;
import com.lucasurbas.marvel.model.ComicPresenterModel;

import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public interface ComicsView extends View {

    void showComicList(List<ComicPresenterModel> comicList);

    void showProgressIndicator(boolean show);

    void showError(String message);

    void setInfoText(String text);
}
