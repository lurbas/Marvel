package com.lucasurbas.marvel.fragment.comics.presenter;

import android.content.Context;

import com.lucasurbas.marvel.R;
import com.lucasurbas.marvel.architecture.BasePresenter;
import com.lucasurbas.marvel.fragment.comics.interactor.ComicsInteractor;
import com.lucasurbas.marvel.fragment.comics.interactor.ComicsInteractorImpl;
import com.lucasurbas.marvel.fragment.comics.view.ComicsView;
import com.lucasurbas.marvel.model.ComicPresenterModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public class ComicsPresenterImpl extends BasePresenter<ComicsInteractor, ComicsView> implements ComicsPresenterForView, ComicsPresenterForInteractor {

    private static final String EMPTY = "";
    private static final int LIMIT = 10;

    private Context context;
    private List<ComicPresenterModel> comicList;

    public ComicsPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public ComicsInteractor createInteractor() {
        return new ComicsInteractorImpl();
    }

    @Override
    public void loadComics() {
        if (getView() != null) {
            getView().showProgressIndicator(true);
            getView().setInfoText(EMPTY);
        }
        getInteractor().loadComics(0, LIMIT);
    }

    @Override
    public void loadMoreComics() {
        getInteractor().loadComics(comicList.size(), LIMIT);
    }

    @Override
    public void showComics(boolean success, List<ComicPresenterModel> list, int offset) {

        if (success) {
            List<ComicPresenterModel> tempComicList = new ArrayList<>();
            for (int i = 0; i < offset; i++) {
                tempComicList.add(this.comicList.get(i));
            }
            tempComicList.addAll(list);
            comicList = tempComicList;
        }

        if (getView() != null) {
            getView().showProgressIndicator(false);
            getView().setInfoText(EMPTY);

            if (success) {
                getView().showComicList(comicList);
                if (comicList.size() <= 0) {
                    getView().setInfoText(context.getString(R.string.t_no_items));
                }
            } else {
                getView().showError(context.getString(R.string.error_connection));
            }
        }
    }
}
