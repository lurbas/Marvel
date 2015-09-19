package com.lucasurbas.marvel.db;

import com.j256.ormlite.stmt.QueryBuilder;
import com.lucasurbas.marvel.constant.Field;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by lucas.urbas on 31/08/15.
 */
public class ORMLiteDatabase implements Database {

    private static final String TAG = ORMLiteDatabase.class.getSimpleName();

    private ORMLiteHelper helper;
    private List<OnTableChangedListener> listenerList;

    public ORMLiteDatabase(ORMLiteHelper helper) {
        this.helper = helper;
        listenerList = Collections.synchronizedList(new ArrayList<OnTableChangedListener>());
    }

    @Override
    public void reset() {
        helper.resetDatabase();
    }

    @Override
    public void addOnTableChangedListener(OnTableChangedListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeOnTableChangedListener(OnTableChangedListener listener) {
        if (listener != null) {
            listenerList.remove(listener);
        }
    }

    @Override
    public <T> T getItem(Class<T> type, Long id) {
        try {
            return helper.getCustomDao(type).queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> List<T> getItemList(Class<T> type, List<Long> idList) {
        try {
            QueryBuilder<T, Long> query = helper.getCustomDao(type).queryBuilder();
            query.where().in(Field.ID, idList);
            query.orderBy(Field.ORDER, true);
            return helper.getCustomDao(type).query(query.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> List<T> getItemList(Class<T> type, int offset, int limit) {
        try {
            QueryBuilder<T, Long> query = helper.getCustomDao(type).queryBuilder();
            query.offset(offset);
            query.limit(limit);
            query.orderBy(Field.ORDER, true);
            return helper.getCustomDao(type).query(query.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> void createOrUpdateItem(Class<T> type, T item) {
        try {
            helper.getCustomDao(type).createOrUpdate(item);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public <T> void createOrUpdateItemList(final Class<T> type, final Collection<T> itemList) {
        try {
            helper.getCustomDao(type).callBatchTasks(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for (T item : itemList) {
                        helper.getCustomDao(type).createOrUpdate(item);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public <T> void refreshItem(Class<T> type, T item) {
        try {
            helper.getCustomDao(type).refresh(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void refreshItemList(final Class<T> type, final Collection<T> itemList) {
        try {
            helper.getCustomDao(type).callBatchTasks(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for (T item : itemList) {
                        helper.getCustomDao(type).refresh(item);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
