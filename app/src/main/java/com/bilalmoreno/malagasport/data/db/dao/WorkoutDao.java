package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.Workout;

import java.util.ArrayList;

public class WorkoutDao {
    public ArrayList<Workout> getAll() {
        ArrayList<Workout> list = new ArrayList<>();

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        Cursor cursor = database.query(
                MalagaSportContract.WorkoutEntry.TABLE_NAME,
                MalagaSportContract.WorkoutEntry.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                MalagaSportContract.WorkoutEntry.SORT_DEFAULT
        );
        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout(
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_LOCATION))
                );
                workout.setLatitude(cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_LATITUDE)));
                workout.setLongitude(cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_LONGITUDE)));

                list.add(workout);
            } while (cursor.moveToNext());
        }

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return list;
    }

    public boolean add(Workout workout) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.WorkoutEntry._ID, workout.getId());
        values.put(MalagaSportContract.WorkoutEntry.COL_NAME, workout.getNombre());
        values.put(MalagaSportContract.WorkoutEntry.COL_LOCATION, workout.getDireccion());
        values.put(MalagaSportContract.WorkoutEntry.COL_LATITUDE, workout.getLatitude());
        values.put(MalagaSportContract.WorkoutEntry.COL_LONGITUDE, workout.getLongitude());

        long result = database.insert(MalagaSportContract.WorkoutEntry.TABLE_NAME, null, values);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result != -1;
    }

    public Workout get(int workoutId) {
        Workout workout = null;
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.WorkoutEntry._ID + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(workoutId)};

        Cursor cursor = database.query(
                MalagaSportContract.WorkoutEntry.TABLE_NAME,
                MalagaSportContract.WorkoutEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            workout = new Workout(
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_NAME)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_LOCATION))
            );
            workout.setLatitude(cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_LATITUDE)));
            workout.setLongitude(cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.WorkoutEntry.COL_LONGITUDE)));
        }

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return workout;    }
}
