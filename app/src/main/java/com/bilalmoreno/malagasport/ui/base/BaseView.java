package com.bilalmoreno.malagasport.ui.base;

public interface BaseView {
    void showProgress();

    void hideProgress();

    void showMessage(int resourceId);

    void showMessage(int resourceId, int iconResourceId);

    void showMessage(String message);

    void showMessage(String message, int iconResourceId);

    void showError(int resourceId);

    void showError(String message);
}
