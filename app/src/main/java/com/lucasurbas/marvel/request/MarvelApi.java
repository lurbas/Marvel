package com.lucasurbas.marvel.request;

import com.lucasurbas.marvel.constant.Url;
import com.lucasurbas.marvel.model.ComicsResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lucas on 29/08/15.
 */
public interface MarvelApi {

    @GET(Url.PATH_COMICS)
    Observable<ComicsResponse> getComics(@Path(Url.PATH_CHARACTER_ID) String characterId, @Query(Url.QUERY_OFFSET) int offset, @Query(Url.QUERY_LIMIT) int limit, @Query(Url.QUERY_TS) String ts,
                                         @Query(Url.QUERY_API_KEY) String publicKey, @Query(Url.QUERY_HASH) String hash);
}
