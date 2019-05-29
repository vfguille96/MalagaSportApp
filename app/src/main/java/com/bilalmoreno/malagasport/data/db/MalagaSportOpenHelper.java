package com.bilalmoreno.malagasport.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bilalmoreno.malagasport.MalagaSportApplication;

import java.util.concurrent.atomic.AtomicInteger;

public class MalagaSportOpenHelper extends SQLiteOpenHelper {

    private static MalagaSportOpenHelper openHelper;
    private SQLiteDatabase database;
    private AtomicInteger openCounter = new AtomicInteger(0);

    private MalagaSportOpenHelper() {
        super(MalagaSportApplication.getContext(), MalagaSportContract.DATABASE_NAME, null, MalagaSportContract.DATABASE_VERSION);
    }

    public synchronized static MalagaSportOpenHelper getInstance() {
        if (openHelper == null) {
            openHelper = new MalagaSportOpenHelper();
        }
        return openHelper;
    }

    public SQLiteDatabase openDatabase() {
        if (openCounter.incrementAndGet() == 1) {
            database = getWritableDatabase();
        }
        return database;
    }

    public void closeDatabase() {
        if (openCounter.decrementAndGet() == 0) {
            database.close();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL(MalagaSportContract.PRAGMA);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            Log.d("Database", MalagaSportContract.WorkoutEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.WorkoutEntry.SQL_CREATE_ENTRIES);
            Log.d("Database", MalagaSportContract.MachineEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.MachineEntry.SQL_CREATE_ENTRIES);
            Log.d("Database", MalagaSportContract.InstallationEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.InstallationEntry.SQL_CREATE_ENTRIES);
            Log.d("Database", MalagaSportContract.TrackEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.TrackEntry.SQL_CREATE_ENTRIES);
            Log.d("Database", MalagaSportContract.UserEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.UserEntry.SQL_CREATE_ENTRIES);
            Log.d("Database", MalagaSportContract.RateEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.RateEntry.SQL_CREATE_ENTRIES);
            Log.d("Database", MalagaSportContract.FavoriteEntry.SQL_CREATE_ENTRIES);
            db.execSQL(MalagaSportContract.FavoriteEntry.SQL_CREATE_ENTRIES);
//            Log.d("Database", MalagaSportContract.EventEntry.SQL_CREATE_ENTRIES);
//            db.execSQL(MalagaSportContract.EventEntry.SQL_CREATE_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
//            db.execSQL(MalagaSportContract.EventEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.FavoriteEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.RateEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.UserEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.TrackEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.InstallationEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.MachineEntry.SQL_DELETE_ENTRIES);
            db.execSQL(MalagaSportContract.WorkoutEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }
}
