package com.lucasurbas.marvel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lucasurbas.marvel.model.Comic;
import com.lucasurbas.marvel.model.Image;

import java.sql.SQLException;

/**
 * Created by lucas.urbas on 31/08/15.
 */
public class ORMLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "database";

    public ORMLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Comic.class);
            TableUtils.createTable(connectionSource, Image.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // for now, upgrades on DB version, consist on drop all DB..

        try {
            TableUtils.dropTable(connectionSource, Comic.class, true);
            TableUtils.dropTable(connectionSource, Image.class, true);

            TableUtils.createTable(connectionSource, Comic.class);
            TableUtils.createTable(connectionSource, Image.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void resetDatabase() {
        try {
            // Drop tables
            TableUtils.dropTable(connectionSource, Comic.class, true);
            TableUtils.dropTable(connectionSource, Image.class, true);
            // Create Tables
            TableUtils.createTable(connectionSource, Comic.class);
            TableUtils.createTable(connectionSource, Image.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Dao<T, Long> getCustomDao(Class<T> clazz) {
        try {
            return getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
