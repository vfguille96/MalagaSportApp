package com.bilalmoreno.malagasport.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.ui.dialog.ProgressBarFragment;

public class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressBarFragment progressDialog;

    @Override
    public void showProgress() {
        progressDialog = new ProgressBarFragment();
        progressDialog.setCancelable(false);
        progressDialog.show(getSupportFragmentManager(), ProgressBarFragment.TAG);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(int resourceId) {
        if (resourceId != 0) {
            showMessage(getString(resourceId));
        } else {
            showMessage(getString(R.string.msg_error_general));
        }
    }

    @Override
    public void showMessage(int resourceId, int iconResourceId) {
        if (resourceId != 0 && iconResourceId != 0) {
            showMessage(getString(resourceId), iconResourceId);
        } else {
            showMessage(getString(R.string.msg_error_general));
        }
    }

    @Override
    public void showMessage(@Nullable String message) {
        if (message != null) {
            showMessage(message, 0);
        } else {
            showMessage(getString(R.string.msg_error_general), 0);
        }
    }

    @Override
    public void showMessage(String message, int iconResourceId) {
        Snackbar snackbar;
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        View view = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.snackbar, null);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        if (iconResourceId != 0) {
            ImageView ivIcon = view.findViewById(R.id.ivIcon);
            ivIcon.setImageResource(iconResourceId);
            ivIcon.setContentDescription(message);
            ivIcon.setVisibility(View.VISIBLE);
        }

        layout.setPadding(8, 8, 8, 8);
        layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        layout.addView(view);
        snackbar.show();
    }

    @Override
    public void showError(int resourceId) {
        showMessage(resourceId, R.drawable.ic_action_warning);
    }

    @Override
    public void showError(String message) {
        showMessage(message, R.drawable.ic_action_warning);
    }

}
