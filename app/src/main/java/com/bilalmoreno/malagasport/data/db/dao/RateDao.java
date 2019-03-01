package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.Rate;

import java.text.ParseException;
import java.util.ArrayList;

public class RateDao {

    public ArrayList<Rate> getList(int installationId) {
        ArrayList<Rate> list = new ArrayList<>();

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.RateEntry.COL_INSTALLATION + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(installationId)};

        Cursor cursor = database.query(
                MalagaSportContract.RateEntry.TABLE_NAME,
                MalagaSportContract.RateEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                MalagaSportContract.RateEntry.SORT_DEFAULT
        );

        if (cursor.moveToFirst()) {
            do {
                Rate rate = null;
                try {
                    rate = new Rate(
                            cursor.getInt(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_INSTALLATION)),
                            cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_USER)),
                            MalagaSportApplication.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_DATE))),
                            cursor.getInt(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_STARS)),
                            cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_COMMENT))
                    );

                    rate.setFechaEdicion(MalagaSportApplication.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_EDIT_DATE))));

                    list.add(rate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return list;
    }

    public boolean add(Rate rate) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.RateEntry.COL_INSTALLATION, rate.getIdInstlacion());
        values.put(MalagaSportContract.RateEntry.COL_USER, rate.getIdUsuario());
        values.put(MalagaSportContract.RateEntry.COL_DATE, MalagaSportApplication.DATE_FORMAT.format(rate.getFecha()));
        values.put(MalagaSportContract.RateEntry.COL_EDIT_DATE, MalagaSportApplication.DATE_FORMAT.format(rate.getFechaEdicion()));
        values.put(MalagaSportContract.RateEntry.COL_STARS, rate.getEstrellas());
        values.put(MalagaSportContract.RateEntry.COL_COMMENT, rate.getComentario());

        long result = database.insert(MalagaSportContract.RateEntry.TABLE_NAME, null, values);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result != -1; //Verdad si la inserción fué satisfactoria.
    }

    public Rate get(String idUsuario, int idInstalacion) {
        Rate rate = null;

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.RateEntry.COL_USER + " = ?, " + MalagaSportContract.RateEntry.COL_INSTALLATION + " = ?" ;
        String[] selectionArgs = new String[] {idUsuario, String.valueOf(idInstalacion)};

        Cursor cursor = database.query(
                MalagaSportContract.RateEntry.TABLE_NAME,
                MalagaSportContract.RateEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            try {
                rate = new Rate(
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_INSTALLATION)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_USER)),
                        MalagaSportApplication.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_DATE))),
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_STARS)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_COMMENT))
                );
                rate.setFechaEdicion(MalagaSportApplication.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(MalagaSportContract.RateEntry.COL_EDIT_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return rate;
    }

    public boolean edit(Rate rate) {
        SQLiteDatabase sqLiteDatabase = MalagaSportOpenHelper.getInstance().openDatabase();

        String whereClause = MalagaSportContract.RateEntry.COL_USER + " = ?, " + MalagaSportContract.RateEntry.COL_INSTALLATION + " = ?";
        String[] whereArgs = new String[] {rate.getIdUsuario(), String.valueOf(rate.getIdInstlacion())};

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.RateEntry.COL_INSTALLATION, rate.getIdInstlacion());
        values.put(MalagaSportContract.RateEntry.COL_USER, rate.getIdUsuario());
        values.put(MalagaSportContract.RateEntry.COL_DATE, MalagaSportApplication.DATE_FORMAT.format(rate.getFecha()));
        values.put(MalagaSportContract.RateEntry.COL_EDIT_DATE, MalagaSportApplication.DATE_FORMAT.format(rate.getFechaEdicion()));
        values.put(MalagaSportContract.RateEntry.COL_STARS, rate.getEstrellas());
        values.put(MalagaSportContract.RateEntry.COL_COMMENT, rate.getComentario());

        int result = sqLiteDatabase.update(
                MalagaSportContract.RateEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result == 1;
    }

    public boolean hasUserRated(String userId, int installationId) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.RateEntry.COL_USER + " = ?, " + MalagaSportContract.RateEntry.COL_INSTALLATION + " = ?";
        String[] selectionArgs = new String[] {userId, String.valueOf(installationId)};

        Cursor cursor = database.query(
                MalagaSportContract.RateEntry.TABLE_NAME,
                MalagaSportContract.RateEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor.getCount() == 1;
    }

    public boolean delete(Rate rate) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String whereClause = MalagaSportContract.RateEntry.COL_USER + " = ?, " + MalagaSportContract.RateEntry.COL_INSTALLATION + " = ?";
        String[] whereArgs = new String[] {rate.getIdUsuario(), String.valueOf(rate.getIdInstlacion())};

        int result = database.delete(
                MalagaSportContract.RateEntry.TABLE_NAME,
                whereClause,
                whereArgs
        );

        return result == 1;
    }
}
