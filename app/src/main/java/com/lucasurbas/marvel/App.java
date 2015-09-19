package com.lucasurbas.marvel;

import android.app.Application;

import com.lucasurbas.marvel.dagger.InjectionModule;

import dagger.ObjectGraph;

/**
 * Created by Lucas on 29/08/15.
 */
public class App extends Application {

    private static ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new InjectionModule(this));
    }

    public static ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
