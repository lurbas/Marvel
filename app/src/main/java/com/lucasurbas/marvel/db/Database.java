package com.lucasurbas.marvel.db;

import java.util.Collection;
import java.util.List;

/**
 * Created by lucas.urbas on 31/08/15.
 */
public interface Database {

    void reset();

    void addOnTableChangedListener(OnTableChangedListener listener);

    void removeOnTableChangedListener(OnTableChangedListener listener);

    <T> T getItem(Class<T> type, Long id);

    <T> List<T> getItemList(Class<T> type, List<Long> idList);

    <T> List<T> getItemList(Class<T> type, int offset, int limit);

    <T> void createOrUpdateItem(Class<T> type, T item);

    <T> void createOrUpdateItemList(Class<T> type, Collection<T> itemList);

    <T> void refreshItem(Class<T> type, T item);

    <T> void refreshItemList(Class<T> type, Collection<T> itemList);

}
