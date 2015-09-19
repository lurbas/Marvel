package com.lucasurbas.marvel.event;

import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 31/08/15.
 */
public class OpenDetailScreenEvent {

    private ComicPresenterModel comic;

    public OpenDetailScreenEvent(ComicPresenterModel comic){
        this.comic = comic;
    }

    public ComicPresenterModel getComic() {
        return comic;
    }
}
