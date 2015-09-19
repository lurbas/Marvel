package com.lucasurbas.marvel.dagger;

import com.google.gson.Gson;
import com.lucasurbas.marvel.constant.Url;
import com.lucasurbas.marvel.request.GetComicsRequest;
import com.lucasurbas.marvel.request.MarvelApi;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Lucas on 29/08/15.
 */
@Module(
        complete = false,
        library = true
)
public class ApiModule {

    @Provides
    @Singleton
    MarvelApi providesApi(Gson gson, OkHttpClient client) {

        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(client))
                .setEndpoint(Url.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();

        return restAdapter.create(MarvelApi.class);
    }

    @Provides
    GetComicsRequest providesComics(MarvelApi marvelApi){
        return new GetComicsRequest(marvelApi);
    }
}
