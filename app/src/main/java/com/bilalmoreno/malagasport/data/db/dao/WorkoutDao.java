package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.Machine;
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
//                workout.setMaquinas(getMachines(workout.getId()));
                list.add(workout);
            } while (cursor.moveToNext());
        }

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
        String[] selectionArgs = new String[]{String.valueOf(workoutId)};

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

//        workout.setMaquinas(getMachines(workoutId));

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return workout;
    }

//    private ArrayList<Machine> getMachines(int workoutId) {
//        ArrayList<Machine> machines = new ArrayList<>();
//        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();
//
//        String selection = MalagaSportContract.MachineEntry.COL_WORKOUT + " = " + workoutId;
//
//        Cursor cursor = database.query(
//                MalagaSportContract.MachineEntry.TABLE_NAME,
//                MalagaSportContract.MachineEntry.ALL_COLUMNS,
//                selection,
//                null,
//                null,
//                null,
//                MalagaSportContract.MachineEntry.SORT_DEFAULT);
//        if (cursor.moveToFirst()) {
//            do {
//                Machine machine = new Machine(
//                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry._ID)),
//                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_NAME)),
//                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_LEVEL)),
//                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_FUNCTION)),
//                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_DEVELOPMENT)),
//                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_CAUTIONS))
//                );
//                machine.setWorkout(cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_WORKOUT)));
//                machines.addInstallations(machine);
//            } while (cursor.moveToNext());
//        }
//
//        MalagaSportOpenHelper.getInstance().closeDatabase();
//        return machines;
//    }

    public boolean update(Workout workout) {
        int result = 0;

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String whereClause = MalagaSportContract.WorkoutEntry._ID + " = " + workout.getId();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.WorkoutEntry.COL_NAME, workout.getNombre());
        values.put(MalagaSportContract.WorkoutEntry.COL_LOCATION, workout.getDireccion());
        values.put(MalagaSportContract.WorkoutEntry.COL_LATITUDE, workout.getLatitude());
        values.put(MalagaSportContract.WorkoutEntry.COL_LONGITUDE, workout.getLongitude());

        result = database.update(MalagaSportContract.WorkoutEntry.TABLE_NAME, values, whereClause, null);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result == 1;
    }

    public boolean update(ArrayList<Workout> workouts) {
        int result = 0;

       SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        for (Workout workout :
                workouts) {
            String whereClause = MalagaSportContract.WorkoutEntry._ID + " = " + workout.getId();

            ContentValues values = new ContentValues();
            values.put(MalagaSportContract.WorkoutEntry.COL_NAME, workout.getNombre());
            values.put(MalagaSportContract.WorkoutEntry.COL_LOCATION, workout.getDireccion());
            values.put(MalagaSportContract.WorkoutEntry.COL_LATITUDE, workout.getLatitude());
            values.put(MalagaSportContract.WorkoutEntry.COL_LONGITUDE, workout.getLongitude());

            Cursor cursor = database.query(MalagaSportContract.WorkoutEntry.TABLE_NAME, MalagaSportContract.WorkoutEntry.ALL_COLUMNS, whereClause, null, null, null, null);
            if (cursor.moveToFirst()) {
                result += database.update(MalagaSportContract.WorkoutEntry.TABLE_NAME, values, whereClause, null);
            } else {
                values.put(MalagaSportContract.WorkoutEntry._ID, workout.getId());
                if (database.insert(MalagaSportContract.WorkoutEntry.TABLE_NAME, null, values) != -1) {
                    result++;
                }
            }
            cursor.close();
        }

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result == 1;
    }

    public boolean add(ArrayList<Workout> addWorkouts) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();
        int result = 0;

        for (Workout workout :
                addWorkouts) {
            ContentValues values = new ContentValues();
            values.put(MalagaSportContract.WorkoutEntry._ID, workout.getId());
            values.put(MalagaSportContract.WorkoutEntry.COL_NAME, workout.getNombre());
            values.put(MalagaSportContract.WorkoutEntry.COL_LOCATION, workout.getDireccion());
            values.put(MalagaSportContract.WorkoutEntry.COL_LATITUDE, workout.getLatitude());
            values.put(MalagaSportContract.WorkoutEntry.COL_LONGITUDE, workout.getLongitude());

            if (database.insert(MalagaSportContract.WorkoutEntry.TABLE_NAME, null, values) != -1) {
                result++;
            }
        }
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result > 0;
    }
}
