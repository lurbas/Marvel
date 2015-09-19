package com.lucasurbas.marvel.request;

import com.lucasurbas.marvel.constant.Url;
import com.lucasurbas.marvel.model.ComicsResponse;
import com.squareup.okhttp.internal.Util;

import rx.Observable;

/**
 * Created by Lucas on 29/08/15.
 */
public class GetComicsRequest {

    private MarvelApi marvelApi;

    public GetComicsRequest(MarvelApi marvelApi) {
        this.marvelApi = marvelApi;
    }

    public Observable<ComicsResponse> getComics(String characterId, int offset, int limit) {
        String ts = String.valueOf(System.currentTimeMillis());
        //md5(ts+privateKey+publicKey)
        String hash = Util.md5Hex(ts + Url.VALUE_PRIVATE_KEY + Url.VALUE_PUBLIC_KEY);
        return marvelApi.getComics(characterId, offset, limit, ts, Url.VALUE_PUBLIC_KEY, hash);
    }
}
