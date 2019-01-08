package com.bilalmoreno;

import android.app.Application;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class MalagaSportApplication extends Application {

    public static final String LOGIN_DATA_TAG = "loginData";
    public static final String USER_TAG = "userEmail";
    public static final String PASSWORD_TAG = "password";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        MalagaSportApplication.userId = userId;
    }
}
