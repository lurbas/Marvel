package com.lucasurbas.marvel.fragment.comics.interactor;

import com.lucasurbas.marvel.App;
import com.lucasurbas.marvel.architecture.BaseInteractor;
import com.lucasurbas.marvel.constant.Ids;
import com.lucasurbas.marvel.db.Database;
import com.lucasurbas.marvel.fragment.comics.presenter.ComicsPresenterForInteractor;
import com.lucasurbas.marvel.model.Comic;
import com.lucasurbas.marvel.model.ComicPresenterModel;
import com.lucasurbas.marvel.model.ComicsResponse;
import com.lucasurbas.marvel.model.Image;
import com.lucasurbas.marvel.request.GetComicsRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lucas on 29/08/15.
 */
public class ComicsInteractorImpl extends BaseInteractor<ComicsPresenterForInteractor> implements ComicsInteractor {

    private static final String TAG = ComicsInteractorImpl.class.getSimpleName();

    @Inject
    GetComicsRequest getComicsRequest;
    @Inject
    Database database;

    public ComicsInteractorImpl() {
        App.getObjectGraph().inject(this);
    }

    @Override
    public void loadComics(final int offset, final int limit) {

        //check in db
        Observable.just(Ids.SPIDER_MAN_ID)
                .map(new Func1<String, List<Comic>>() {
                    @Override
                    public List<Comic> call(String characterId) {

                        List<Comic> comicsFromDb = database.getItemList(Comic.class, offset, limit);
                        if (comicsFromDb != null) {

                            return comicsFromDb;
                        } else {
                            return new ArrayList<Comic>();
                        }
                    }
                })
                .map(CONVERT_TO_PRESENTER_MODEL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ComicPresenterModel>>() {
                    @Override
                    public void call(final List<ComicPresenterModel> comicList) {
                        if (isPresenterSubscribed()) {
                            if (!comicList.isEmpty()) {
                                getPresenter().showComics(true, comicList, offset);
                            }

                            //api request
                            getComicsRequest.getComics(Ids.SPIDER_MAN_ID, offset, limit)
                                    .map(new Func1<ComicsResponse, List<Comic>>() {
                                        @Override
                                        public List<Comic> call(ComicsResponse response) {

                                            List<Comic> comicsFromApi = response.getComics();
                                            for (int i = 0; i < comicsFromApi.size(); i++) {
                                                Comic c = comicsFromApi.get(i);
                                                c.setOrder(offset + i);
                                                database.createOrUpdateItem(Image.class, c.getThumbnail());

                                                for(Image img : c.getImages()){
                                                    img.setComic(c);
                                                }
                                                database.createOrUpdateItemList(Image.class, c.getImages());
                                            }
                                            database.createOrUpdateItemList(Comic.class, comicsFromApi);

                                            return comicsFromApi;
                                        }
                                    })
                                    .map(CONVERT_TO_PRESENTER_MODEL)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<List<ComicPresenterModel>>() {
                                        @Override
                                        public void call(List<ComicPresenterModel> comicList) {
                                            if (isPresenterSubscribed()) {
                                                getPresenter().showComics(true, comicList, offset);
                                            }
                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {
                                            if (isPresenterSubscribed()) {
                                                getPresenter().showComics(false, null, offset);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    private final Func1<List<Comic>, List<ComicPresenterModel>> CONVERT_TO_PRESENTER_MODEL =
            new Func1<List<Comic>, List<ComicPresenterModel>>() {
                @Override
                public List<ComicPresenterModel> call(List<Comic> comicList) {

                    List<ComicPresenterModel> comicPresenterModels = new ArrayList<>();
                    for (Comic c : comicList) {
                        comicPresenterModels.add(new ComicPresenterModel(c));
                    }
                    return comicPresenterModels;
                }
            };
}
