package com.lucasurbas.marvel.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucasurbas.marvel.db.Database;
import com.lucasurbas.marvel.db.ORMLiteDatabase;
import com.lucasurbas.marvel.db.ORMLiteHelper;
import com.lucasurbas.marvel.fragment.comics.interactor.ComicsInteractorImpl;
import com.lucasurbas.marvel.fragment.detail.DetailFragment;
import com.lucasurbas.marvel.fragment.detail.interactor.DetailInteractorImpl;
import com.lucasurbas.marvel.widget.ComicViewHolder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lucas on 29/08/15.
 */
@Module(
        library = true,
        injects = {
                ComicsInteractorImpl.class,
                ComicViewHolder.class,
                DetailInteractorImpl.class,
                DetailFragment.class
        },
        includes = {
                ApiModule.class
        })
public class InjectionModule {

    private Context applicationContext;

    public InjectionModule(Context context) {
        this.applicationContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    Picasso providesPicasso() {
        return Picasso.with(applicationContext);
    }

    @Provides
    Gson providesGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    OkHttpClient providesOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        return client;
    }

    @Provides
    @Singleton
    ORMLiteHelper providesDatabaseHelper(Context context) {
        return new ORMLiteHelper(context);
    }

    @Provides
    @Singleton
    Database providesDatabase(ORMLiteHelper helper) {
        return new ORMLiteDatabase(helper);
    }
}
