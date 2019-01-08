package com.bilalmoreno.malagasport.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.R;

public class BaseFragment extends Fragment implements BaseView {

    private ProgressDialogFragment progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialogFragment();
        progressDialog.setCancelable(false);
        progressDialog.show(getFragmentManager(), ProgressDialogFragment.TAG);
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
        snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        View view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.snackbar, null);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        if (iconResourceId != 0) {
            ImageView ivIcon = view.findViewById(R.id.ivIcon);
            ivIcon.setImageResource(iconResourceId);
            ivIcon.setContentDescription(message);
            ivIcon.setVisibility(View.VISIBLE);
        }

        layout.setPadding(5, 0, 0, 0);
        layout.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));
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

    public interface PrimaryActionButton {
        void onPrimaryActionButtonHide();

        void onPrimaryActionButtonShow(int iconResourceId, View.OnClickListener listener);
    }
}
