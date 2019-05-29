package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.Machine;

import java.util.ArrayList;

public class MachineDao {
    public ArrayList<Machine> getAll() {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();
        ArrayList<Machine> list = new ArrayList<>();

        Cursor cursor = database.query(
                true,
                MalagaSportContract.MachineEntry.TABLE_NAME,
                MalagaSportContract.MachineEntry.INFO_COLUMNS,
                null,
                null,
                null,
                null,
                MalagaSportContract.MachineEntry.SORT_DEFAULT,
                null
        );

        int id = 0;
        if (cursor.moveToFirst()) {
            do {
                Machine machine = new Machine(
                        ++id,
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_LEVEL)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_FUNCTION)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_DEVELOPMENT)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_CAUTIONS))
                );

//                if (!list.contains(machine)) { //If no necesario si se hace select distinct sin incluir las columnas id y workout.
                list.add(machine);
//                }

            } while (cursor.moveToNext());
        }

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return list;
    }

    public boolean add(Machine machine) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.MachineEntry._ID, machine.getId());
        values.put(MalagaSportContract.MachineEntry.COL_NAME, machine.getNombre());
        values.put(MalagaSportContract.MachineEntry.COL_LEVEL, machine.getNivel());
        values.put(MalagaSportContract.MachineEntry.COL_FUNCTION, machine.getFuncion());
        values.put(MalagaSportContract.MachineEntry.COL_DEVELOPMENT, machine.getDesarollo());
        values.put(MalagaSportContract.MachineEntry.COL_CAUTIONS, machine.getPrecauciones());
        values.put(MalagaSportContract.MachineEntry.COL_WORKOUT, machine.getWorkout());

        long result = database.insert(MalagaSportContract.MachineEntry.TABLE_NAME, null, values);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result != -1; //Verdad si la inserción fué satisfactoria.
    }

    public Machine get(int machineId) {
        Machine machine = null;

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.MachineEntry._ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(machineId)};

        Cursor cursor = database.query(
                MalagaSportContract.MachineEntry.TABLE_NAME,
                MalagaSportContract.MachineEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            machine = new Machine(
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_NAME)),
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_LEVEL)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_FUNCTION)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_DEVELOPMENT)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_CAUTIONS))
            );
            machine.setWorkout(cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_WORKOUT)));
        }

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return machine;
    }

    public ArrayList<Machine> getList(int workoutId) {
        ArrayList<Machine> list = new ArrayList<>();

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.MachineEntry.COL_WORKOUT + " = " + workoutId;

        Cursor cursor = database.query(
                MalagaSportContract.MachineEntry.TABLE_NAME,
                MalagaSportContract.MachineEntry.ALL_COLUMNS,
                selection,
                null,
                null,
                null,
                MalagaSportContract.MachineEntry.SORT_DEFAULT + ", " + MalagaSportContract.MachineEntry.COL_NAME
        );

        if (cursor.moveToFirst()) {
            do {
                Machine machine = new Machine(
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_LEVEL)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_FUNCTION)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_DEVELOPMENT)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_CAUTIONS))
                );
                machine.setWorkout(cursor.getInt(cursor.getColumnIndex(MalagaSportContract.MachineEntry.COL_WORKOUT)));

                list.add(machine);
            } while (cursor.moveToNext());
        }

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return list;
    }

    public boolean update(Machine machine) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        int result = 0;

        String whereClause = MalagaSportContract.MachineEntry._ID + " = " + machine.getId();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.MachineEntry.COL_NAME, machine.getNombre());
        values.put(MalagaSportContract.MachineEntry.COL_LEVEL, machine.getNivel());
        values.put(MalagaSportContract.MachineEntry.COL_FUNCTION, machine.getFuncion());
        values.put(MalagaSportContract.MachineEntry.COL_DEVELOPMENT, machine.getDesarollo());
        values.put(MalagaSportContract.MachineEntry.COL_CAUTIONS, machine.getPrecauciones());
        values.put(MalagaSportContract.MachineEntry.COL_WORKOUT, machine.getWorkout());

        result = database.update(MalagaSportContract.MachineEntry.TABLE_NAME, values, whereClause, null);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result == 1;
    }
}
