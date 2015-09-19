package com.lucasurbas.marvel.fragment.detail.presenter;

import android.content.Context;
import android.util.Log;

import com.lucasurbas.marvel.architecture.BasePresenter;
import com.lucasurbas.marvel.fragment.detail.interactor.DetailInteractor;
import com.lucasurbas.marvel.fragment.detail.interactor.DetailInteractorImpl;
import com.lucasurbas.marvel.fragment.detail.view.DetailView;
import com.lucasurbas.marvel.model.ComicPresenterModel;

import java.util.List;
import java.util.Random;


/**
 * Created by Lucas on 30/08/15.
 */
public class DetailPresenterImpl extends BasePresenter<DetailInteractor, DetailView> implements DetailPresenterForView, DetailPresenterForInteractor {

    private static final String TAG = DetailPresenterImpl.class.getSimpleName();

    private Context context;
    private ComicPresenterModel comic;

    public DetailPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public DetailInteractor createInteractor() {
        return new DetailInteractorImpl();
    }

    @Override
    public void initComic(ComicPresenterModel comic) {
        this.comic = comic;
        if (getView() != null) {
            getView().setComicData(comic);
        }
        getInteractor().refreshComicImages(comic);
    }

    @Override
    public void shuffleImage() {
        if (comic != null) {
            shuffleImage(comic);
        }
    }

    @Override
    public void setComicImages(ComicPresenterModel comic, boolean success) {
        this.comic = comic;
        shuffleImage(comic);
    }

    private void shuffleImage(ComicPresenterModel comic) {
        final List<String> images = comic.getImageUrlList();
        int size = images == null ? -1 : images.size();
        Log.v(TAG, "shuffleImage: " + size);
        if (images != null && !images.isEmpty()) {
            Random r = new Random();
            int i = r.nextInt(images.size());
            if (getView() != null) {
                getView().setImage(images.get(i));
            }
        }
    }
}
