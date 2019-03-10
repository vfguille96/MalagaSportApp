package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.User;

import java.text.ParseException;

public class UserDao {
    public boolean validateCredentials(String email, String password) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.UserEntry._ID + " = '" + email +"' AND " + MalagaSportContract.UserEntry.COL_PASSWORD + " = '" + password + "'";

        Cursor cursor = database.query(
                MalagaSportContract.UserEntry.TABLE_NAME,
                MalagaSportContract.UserEntry.ALL_COLUMNS,
                selection,
                null,
                null,
                null,
                null
        );

        int result = cursor.getCount();

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result == 1;
    }

    public User getUser(String email) {
        User user = new User("NoUser");
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.UserEntry._ID + " = ?";
        String[] selectionArgs = new String[] {email};

        Cursor cursor = database.query(
                MalagaSportContract.UserEntry.TABLE_NAME,
                MalagaSportContract.UserEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            try {
                user = new User(
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.UserEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.UserEntry.COL_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.UserEntry.COL_NAME)),
                        MalagaSportApplication.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(MalagaSportContract.UserEntry.COL_BIRTHDAY)))
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

//        cursor.close();
        MalagaSportOpenHelper.getInstance().closeDatabase();

        return user;
    }

    public boolean add(User user, String password) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.UserEntry._ID, user.getId());
        values.put(MalagaSportContract.UserEntry.COL_EMAIL, user.getEmail());
        values.put(MalagaSportContract.UserEntry.COL_PASSWORD, password);
        values.put(MalagaSportContract.UserEntry.COL_NAME, user.getNombre());
        values.put(MalagaSportContract.UserEntry.COL_BIRTHDAY, MalagaSportApplication.DATE_FORMAT.format(user.getFechaNacimiento()));
        values.put(MalagaSportContract.UserEntry.COL_PHOTO, user.getFotoURL());

        long result = database.insert(MalagaSportContract.UserEntry.TABLE_NAME, null, values);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result != -1; //Verdad si la inserción fué satisfactoria.
    }

    public boolean changePassword(String email, String password) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.UserEntry.COL_EMAIL, email.toLowerCase());
        values.put(MalagaSportContract.UserEntry.COL_PASSWORD, password);

        String whereClause = MalagaSportContract.UserEntry._ID + " = ?";
        String[] whereArgs = new String[] {email.toLowerCase()};

        int result = database.update(
                MalagaSportContract.UserEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result == 1;
    }
}
