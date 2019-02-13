package com.bilalmoreno.malagasport;

import android.app.Application;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class MalagaSportApplication extends Application {

    /*
    Tags para las propiedades compartidas de la app.
     */
    public static final String LOGIN_DATA_TAG = "loginData";
    public static final String USER_TAG = "userEmail";
    public static final String PASSWORD_TAG = "password";
    /*
    Requisitos email
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /*
    Rquisitos password
     */
    public static final int MIN_PASSWORD_LENGTH = 6;
    /*
    Formato de fecha por defecto
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    /*
    Tags settings
     */
    public static final String SETTINGS = "settings";
    public static final String METEO_ON_MAP = "meteo_on_map";
    /*
    ID del usuario que inicia sesión
     */
    private static String userId;

    /**
     * Metodo que devuelve el ID del usuario de la sesión actual.
     *
     * @return
     */
    public static String getUserId() {
        return userId;
    }

    /**
     * Método que establece el ID del usuario de la sesión actual.
     *
     * @param userId
     */
    public static void setUserId(String userId) {
        MalagaSportApplication.userId = userId;
    }
}
