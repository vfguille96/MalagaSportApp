package com.bilalmoreno.malagasport.data.db;

import android.provider.BaseColumns;

public final class MalagaSportContract {
    public static final String DATABASE_NAME = "malaga.db";
    public static final int DATABASE_VERSION = 1;
    public static final String PRAGMA = "PRAGMA foreign_keys=ON";

    public static final class WorkoutEntry implements BaseColumns {
        //Nombre de la tabla
        public static final String TABLE_NAME = "workout";

        //Columnas de la tabla
        public static final String COL_NAME = "name";
        public static final String COL_LOCATION = "location";
        public static final String COL_LATITUDE = "latitude";
        public static final String COL_LONGITUDE = "longitude";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{_ID, COL_NAME, COL_LOCATION, COL_LATITUDE, COL_LONGITUDE};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_NAME;

        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s REAL NOT NULL, " +
                        "%s REAL NOT NULL)",
                TABLE_NAME,
                _ID,
                COL_NAME,
                COL_LOCATION,
                COL_LATITUDE,
                COL_LONGITUDE);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class MachineEntry implements BaseColumns {
        //Nombre de la tabla
        public static final String TABLE_NAME = "machine";

        //Columnas de la tabla
        public static final String COL_NAME = "name";
        public static final String COL_LEVEL = "level";
        public static final String COL_FUNCTION = "function";
        public static final String COL_DEVELOPMENT = "development";
        public static final String COL_CAUTIONS = "cautions";
        public static final String COL_WORKOUT = "workout";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{_ID, COL_NAME, COL_LEVEL, COL_FUNCTION, COL_DEVELOPMENT, COL_CAUTIONS, COL_WORKOUT};

        //Columnas para el listado de tipos de máquinas (select distinct sin tener en cuenta el número de serie ni la zona de workout)
        public static final String[] INFO_COLUMNS = new String[]{COL_NAME, COL_LEVEL, COL_FUNCTION, COL_DEVELOPMENT, COL_CAUTIONS};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_LEVEL;

        //Claves foraneas
        public static final String REFERENCE_WORKOUT = String.format("FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COL_WORKOUT,
                WorkoutEntry.TABLE_NAME,
                WorkoutEntry._ID);

        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s)",
                TABLE_NAME,
                _ID,
                COL_NAME,
                COL_LEVEL,
                COL_FUNCTION,
                COL_DEVELOPMENT,
                COL_CAUTIONS,
                COL_WORKOUT,
                REFERENCE_WORKOUT);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class InstallationEntry implements BaseColumns {
        //Nombre de la tabla
        public static final String TABLE_NAME = "instalation";

        //Columnas de la tabla
        public static final String COL_NAME = "name";
        public static final String COL_ADDRESS = "address";
        public static final String COL_LATITUDE = "latitude";
        public static final String COL_LONGITUDE = "longitude";
        public static final String COL_YOUNG_CARD = "young_card"; //tarjeta joven
        public static final String COL_BARRIER_FREE = "barrier_free"; //acceso movilidad reducida
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_WEB = "web";
        public static final String COL_EMAIL = "email";
        public static final String COL_PHONE = "phone";
        public static final String COL_SCHEDULE = "schedule"; //horario
        public static final String COL_PRICE = "price";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{_ID, COL_NAME, COL_ADDRESS, COL_LATITUDE, COL_LONGITUDE, COL_YOUNG_CARD, COL_BARRIER_FREE, COL_DESCRIPTION, COL_WEB, COL_EMAIL, COL_PHONE, COL_SCHEDULE, COL_PRICE};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_NAME;

        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s REAL NOT NULL, " +
                        "%s REAL NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT)",
                TABLE_NAME,
                _ID,
                COL_NAME,
                COL_ADDRESS,
                COL_LATITUDE,
                COL_LONGITUDE,
                COL_YOUNG_CARD,
                COL_BARRIER_FREE,
                COL_DESCRIPTION,
                COL_WEB,
                COL_EMAIL,
                COL_PHONE,
                COL_SCHEDULE,
                COL_PRICE);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class TrackEntry implements BaseColumns {
        //Nombre de la tabla
        public static final String TABLE_NAME = "track";

        //Columnas de la tabla
        public static final String COL_NAME = "name";
        public static final String COL_ILLUMINATION = "illumination";
        public static final String COL_DIMENSIONS = "dimensions";
        public static final String COL_ACTIVITY = "activity";
        public static final String COL_MATERIAL = "material";
        public static final String COL_INSTALLATION = "installation";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{_ID, COL_NAME, COL_ILLUMINATION, COL_DIMENSIONS, COL_ACTIVITY, COL_MATERIAL, COL_INSTALLATION};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_ACTIVITY;

        //Claves foraneas
        public static final String REFERENCE_INSTALLATION = String.format("FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COL_INSTALLATION,
                InstallationEntry.TABLE_NAME,
                InstallationEntry._ID);

        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s)",
                TABLE_NAME,
                _ID,
                COL_NAME,
                COL_ILLUMINATION,
                COL_DIMENSIONS,
                COL_ACTIVITY,
                COL_MATERIAL,
                COL_INSTALLATION,
                REFERENCE_INSTALLATION);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class UserEntry implements BaseColumns {
        //Nombre de la tabla
        public static final String TABLE_NAME = "user";

        //Columnas de la tabla
        public static final String COL_EMAIL = "email";
        public static final String COL_PASSWORD = "password";
        public static final String COL_NAME = "name";
        public static final String COL_BIRTHDAY = "birthday";
        public static final String COL_PHOTO = "photo";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{_ID, COL_EMAIL, COL_NAME, COL_BIRTHDAY, COL_PHOTO};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_EMAIL;

        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s TEXT PRIMARY KEY, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT)",
                TABLE_NAME,
                _ID,
                COL_EMAIL,
                COL_PASSWORD,
                COL_NAME,
                COL_BIRTHDAY,
                COL_PHOTO);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class RateEntry {
        //Nombre de la tabla
        public static final String TABLE_NAME = "rate";

        //Columnas de la tabla
        public static final String COL_INSTALLATION = "installation";
        public static final String COL_USER = "user";
        public static final String COL_DATE = "date";
        public static final String COL_EDIT_DATE = "edit_date";
        public static final String COL_STARS = "stars";
        public static final String COL_COMMENT = "commnets";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{COL_INSTALLATION, COL_USER, COL_DATE, COL_EDIT_DATE, COL_STARS, COL_COMMENT};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_EDIT_DATE;

        //Clave primaria
        public static final String PRIMARY_KEY = String.format("PRIMARY KEY (%s, %s)",
                COL_INSTALLATION,
                COL_USER);

        //Claves foraneas
        public static final String REFERENCE_INSTALLATION = String.format("FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COL_INSTALLATION,
                InstallationEntry.TABLE_NAME,
                InstallationEntry._ID);
        public static final String REFERENCE_USER = String.format("FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COL_USER,
                UserEntry.TABLE_NAME,
                UserEntry._ID);


        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT, " +
                        "%s, " +
                        "%s, " +
                        "%s)",
                TABLE_NAME,
                COL_INSTALLATION,
                COL_USER,
                COL_DATE,
                COL_EDIT_DATE,
                COL_STARS,
                COL_COMMENT,
                PRIMARY_KEY,
                REFERENCE_INSTALLATION,
                REFERENCE_USER);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class FavoriteEntry {
        //Nombre de la tabla
        public static final String TABLE_NAME = "favorite";

        //Columnas de la tabla
        public static final String COL_USER = "user";
        public static final String COL_INSTALLATION = "installation";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{COL_USER, COL_INSTALLATION};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_USER;

        //Clave primaria
        public static final String PRIMARY_KEY = String.format("PRIMARY KEY (%s, %s)",
                COL_USER,
                COL_INSTALLATION);

        //Claves foraneas
        public static final String REFERENCE_INSTALLATION = String.format("FOREIGN KEY (%s) REFERENCES %s (%s)",
                COL_INSTALLATION,
                InstallationEntry.TABLE_NAME,
                InstallationEntry._ID);

        //Crear tabla
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s, " +
                        "%s)",
                TABLE_NAME,
                COL_USER,
                COL_INSTALLATION,
                PRIMARY_KEY,
                REFERENCE_INSTALLATION);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static final class EventEntry implements BaseColumns {
        //Nombre de la tabla
        public static final String TABLE_NAME = "event";

        //Columnas de la tabla
        public static final String COL_NAME = "name";
        public static final String COL_INSTALLATION = "installation";
        public static final String COL_PLACE = "place";
        public static final String COL_START_DATE = "start_date";
        public static final String COL_END_DATE = "end_date";
        public static final String COL_SCHEDULE = "schedule";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_BARRIER_FREE = "barrier_free";
        public static final String COL_ORGANIZER = "organizer";
        public static final String COL_FOR = "for";
        public static final String COL_PHONE = "phone";
        public static final String COL_EMAIL = "email";
        public static final String COL_WEB = "web";

        //Todas las columnas
        public static final String[] ALL_COLUMNS = new String[]{_ID, COL_NAME, COL_INSTALLATION, COL_PLACE, COL_START_DATE, COL_END_DATE, COL_SCHEDULE, COL_DESCRIPTION, COL_BARRIER_FREE, COL_ORGANIZER, COL_FOR, COL_PHONE, COL_EMAIL, COL_WEB};

        //Orden por defecto
        public static final String SORT_DEFAULT = COL_START_DATE;

        //TODO Analizar fichero CSV (2019.csv) para terminar de definir la base de datos.
        //Crear tabla (Sentencia icompleta
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s TEXT PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER, " +
                        "%s TEXT, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s)",
                TABLE_NAME,
                _ID);

        //Eliminar tabla
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }
}
