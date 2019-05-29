package com.bilalmoreno.malagasport.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bilalmoreno.malagasport.data.db.MalagaSportContract;
import com.bilalmoreno.malagasport.data.db.MalagaSportOpenHelper;
import com.bilalmoreno.malagasport.data.db.model.Track;

import java.util.ArrayList;

//TODO Dao incompleto, aún no hay una Activity que necesite acceder a estos datos
public class TrackDao {
    public ArrayList<Track> getAll() {
        ArrayList<Track> list = new ArrayList<>();

        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        Cursor cursor = database.query(
                MalagaSportContract.TrackEntry.TABLE_NAME,
                MalagaSportContract.TrackEntry.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                MalagaSportContract.TrackEntry.SORT_DEFAULT
        );

        if (cursor.moveToFirst()) {
            do {
                Track track = new Track(
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.TrackEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_ILLUMINATION)) == 1,
                        cursor.getString(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_DIMENSIONS)),
                        cursor.getInt(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_ACTIVITY))
                );

                track.setPavimento(cursor.getString(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_MATERIAL)));

                list.add(track);
            } while (cursor.moveToNext());
        }

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return list;
    }

    public Track get(int trackId) {
        Track track = null;
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        String selection = MalagaSportContract.TrackEntry._ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(trackId)};

        Cursor cursor = database.query(
                MalagaSportContract.TrackEntry.TABLE_NAME,
                MalagaSportContract.TrackEntry.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            track = new Track(
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.TrackEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_NAME)),
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_ILLUMINATION)) == 1,
                    cursor.getString(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_DIMENSIONS)),
                    cursor.getInt(cursor.getColumnIndex(MalagaSportContract.TrackEntry.COL_ACTIVITY))
            );
        }

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return track;
    }

    public boolean add(Track track, int installationId) {
        SQLiteDatabase database = MalagaSportOpenHelper.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MalagaSportContract.TrackEntry._ID, track.getId());
        values.put(MalagaSportContract.TrackEntry.COL_NAME, track.getNombre());
        values.put(MalagaSportContract.TrackEntry.COL_ILLUMINATION, track.getIluminacion() ? 1 : 0);
        values.put(MalagaSportContract.TrackEntry.COL_DIMENSIONS, track.getDimensiones());
        values.put(MalagaSportContract.TrackEntry.COL_ACTIVITY, track.getActividadDeportiva());
        values.put(MalagaSportContract.TrackEntry.COL_MATERIAL, track.getPavimento());
        values.put(MalagaSportContract.TrackEntry.COL_INSTALLATION, installationId);

        long result = database.insert(MalagaSportContract.MachineEntry.TABLE_NAME, null, values);

        MalagaSportOpenHelper.getInstance().closeDatabase();

        return result != -1; //Verdad si la inserción fué satisfactoria.    }
    }
}
