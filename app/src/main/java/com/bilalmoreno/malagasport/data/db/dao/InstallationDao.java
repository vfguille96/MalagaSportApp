package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.Installation;

import java.util.ArrayList;

public class InstallationDao {
    public ArrayList<Installation> getAll() {
        ArrayList<Installation> list = new ArrayList<>();
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        Cursor cursor = database.query(
                MalagaSportContract.InstallationEntry.TABLE_NAME,
                MalagaSportContract.InstallationEntry.ALL_COLUMNS,
                null,
                null,

                null,
                null,
                MalagaSportContract.InstallationEntry.SORT_DEFAULT
        );

        if (cursor.moveToFirst()) {
            do {
                Installation installation = new Installation(
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.InstallationEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_ADDRESS)),
                        cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_LONGITUDE)),
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_YOUNG_CARD)) == 1,
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_BARRIER_FREE)) == 1
                );
                installation.setDescripcion(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_DESCRIPTION)));
                installation.setWeb(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_WEB)));
                installation.setEmail(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_EMAIL)));
                installation.setTelefono(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_PHONE)));
                installation.setHorario(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_SCHEDULE)));
                installation.setPrecio(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_PRICE)));

                list.add(installation);
            } while (cursor.moveToNext());
        }

        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return list;
    }

    public boolean add(Installation installation) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.InstallationEntry._ID, installation.getId());
        values.put(MalagaSportContract.InstallationEntry.COL_NAME, installation.getNombre());
        values.put(MalagaSportContract.InstallationEntry.COL_ADDRESS, installation.getDireccion());
        values.put(MalagaSportContract.InstallationEntry.COL_LATITUDE, installation.getLatitud());
        values.put(MalagaSportContract.InstallationEntry.COL_LONGITUDE, installation.getLongitud());
        values.put(MalagaSportContract.InstallationEntry.COL_YOUNG_CARD, installation.getTarjetaJoven() ? 1 : 0);
        values.put(MalagaSportContract.InstallationEntry.COL_BARRIER_FREE, installation.getAccesoMovReducida() ? 1 : 0);
        values.put(MalagaSportContract.InstallationEntry.COL_DESCRIPTION, installation.getDescripcion());
        values.put(MalagaSportContract.InstallationEntry.COL_WEB, installation.getWeb());
        values.put(MalagaSportContract.InstallationEntry.COL_EMAIL, installation.getEmail());
        values.put(MalagaSportContract.InstallationEntry.COL_PHONE, installation.getTelefono());
        values.put(MalagaSportContract.InstallationEntry.COL_SCHEDULE, installation.getHorario());
        values.put(MalagaSportContract.InstallationEntry.COL_PRICE, installation.getPrecio());

        long result = database.insert(MalagaSportContract.InstallationEntry.TABLE_NAME, null, values);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result != -1; //Verdad si la inserción fué satisfactoria.
    }

    public Installation get(int installationId) {
        Installation installation = null;
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.InstallationEntry._ID + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(installationId)};

        Cursor cursor = database.query(
                MalagaSportContract.InstallationEntry.TABLE_NAME,
                MalagaSportContract.InstallationEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            installation = new Installation(
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.InstallationEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_NAME)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_ADDRESS)),
                    cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_LATITUDE)),
                    cursor.getDouble(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_LONGITUDE)),
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_YOUNG_CARD)) == 1,
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_BARRIER_FREE)) == 1
            );
            installation.setDescripcion(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_DESCRIPTION)));
            installation.setWeb(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_WEB)));
            installation.setEmail(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_EMAIL)));
            installation.setTelefono(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_PHONE)));
            installation.setHorario(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_SCHEDULE)));
            installation.setPrecio(cursor.getString(cursor.getColumnIndex(MalagaSportContract.InstallationEntry.COL_PRICE)));
        }

        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return installation;
    }
}
