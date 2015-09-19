package com.lucasurbas.marvel.fragment.detail.interactor;

import com.lucasurbas.marvel.App;
import com.lucasurbas.marvel.architecture.BaseInteractor;
import com.lucasurbas.marvel.db.Database;
import com.lucasurbas.marvel.fragment.detail.presenter.DetailPresenterForInteractor;
import com.lucasurbas.marvel.model.Comic;
import com.lucasurbas.marvel.model.ComicPresenterModel;
import com.lucasurbas.marvel.model.Image;

import java.util.Collection;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailInteractorImpl extends BaseInteractor<DetailPresenterForInteractor> implements DetailInteractor {

    private static final String TAG = DetailInteractorImpl.class.getSimpleName();

    @Inject
    Database database;

    public DetailInteractorImpl() {
        App.getObjectGraph().inject(this);
    }

    @Override
    public void refreshComicImages(ComicPresenterModel comic) {

        Observable.just(comic.getId())
                .map(new Func1<Long, Comic>() {
                    @Override
                    public Comic call(Long id) {

                        Comic comic = database.getItem(Comic.class, id);

                        Collection<Image> images = comic.getImages();
                        database.refreshItemList(Image.class, images);

                        return comic;
                    }
                })
                .map(CONVERT_TO_PRESENTER_MODEL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ComicPresenterModel>() {
                    @Override
                    public void call(ComicPresenterModel comicPresenterModel) {
                        if (isPresenterSubscribed()) {
                            getPresenter().setComicImages(comicPresenterModel, true);
                        }
                    }
                });
    }

    private final Func1<Comic, ComicPresenterModel> CONVERT_TO_PRESENTER_MODEL =
            new Func1<Comic, ComicPresenterModel>() {
                @Override
                public ComicPresenterModel call(Comic comic) {

                    return new ComicPresenterModel(comic);
                }
            };
}
