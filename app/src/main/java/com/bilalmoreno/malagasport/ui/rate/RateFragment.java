package com.bilalmoreno.malagasport.ui.rate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.data.db.model.User;
import com.bilalmoreno.malagasport.ui.MainNavigationActivity;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RateFragment extends BaseFragment implements RateContract.View, View.OnClickListener {

    public static final int RATE_ADD = 0;
    public static final int RATE_EDIT = 1;
    public static final String ACTION_TAG = "action";

    int estrellas;
    int idInstalacion;
    int actionCode;
    ImageView ivStar1;
    ImageView ivStar2;
    ImageView ivStar3;
    ImageView ivStar4;
    ImageView ivStar5;
    TextInputLayout tilComentario;
    TextInputEditText tiedComentario;
    private Rate rate;
    private String idUsuario;

    private RateContract.Presenter presenter;
    private OnRateSavedChanges callback;
    private PrimaryActionButton primaryActionButton;
    private OnStarClickListener onStarClickListener;

    public static RateFragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
        RateFragment fragment = new RateFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (primaryActionButton != null) {
            fragment.primaryActionButton = primaryActionButton;
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnRateSavedChanges) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnValoratiionShow");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_rate, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_rate_delete:
                presenter.delete(rate);
                break;
            default:
                return false;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rate, container, false);
        if (rootView != null) {
            ivStar1 = rootView.findViewById(R.id.ivStar1);
            ivStar2 = rootView.findViewById(R.id.ivStar2);
            ivStar3 = rootView.findViewById(R.id.ivStar3);
            ivStar4 = rootView.findViewById(R.id.ivStar4);
            ivStar5 = rootView.findViewById(R.id.ivStar5);
            tilComentario = rootView.findViewById(R.id.tilComentario);
            tiedComentario = rootView.findViewById(R.id.tiedComentario);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new RatePresenter(this);

        onStarClickListener = new OnStarClickListener();

        ivStar1.setOnClickListener(onStarClickListener);
        ivStar2.setOnClickListener(onStarClickListener);
        ivStar3.setOnClickListener(onStarClickListener);
        ivStar4.setOnClickListener(onStarClickListener);
        ivStar5.setOnClickListener(onStarClickListener);

        Bundle bundle = getArguments();
        if (bundle != null) {
            actionCode = bundle.getInt(ACTION_TAG, -1);
            idInstalacion = bundle.getInt(Installation.TAG, -1);
            idUsuario = bundle.getString(User.TAG);
            if (idUsuario != null && actionCode == RATE_EDIT && idInstalacion != -1) {
                presenter.load(idUsuario, idInstalacion);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        primaryActionButton.onPrimaryActionButtonShow(R.drawable.ic_menu_send, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        primaryActionButton.onPrimaryActionButtonHide();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void showRate(Rate rate) {
        if (actionCode == RATE_EDIT) {
            this.rate = rate;
            tiedComentario.setText(rate.getComentario());
            estrellas = rate.getEstrellas();
            switch (estrellas) {
                case 5:
                    ivStar5.setImageResource(R.drawable.ic_fav_32dp);
                case 4:
                    ivStar4.setImageResource(R.drawable.ic_fav_32dp);
                case 3:
                    ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                case 2:
                    ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                case 1:
                    ivStar1.setImageResource(R.drawable.ic_fav_32dp);
            }
        } else {
            this.rate = null;
            estrellas = 0;
        }
    }

    @Override
    public void setCommentEmptyError() {
        tilComentario.setError(getString(R.string.msg_err_empty_comment));
        requestFocus(tiedComentario);
    }

    @Override
    public void onSuccess() {
        callback.onRateSavedChanges();
    }


    @Override
    public void onClick(View v) {
        ((MainNavigationActivity) getActivity()).hideKeyboard();
        if (actionCode == RATE_ADD) {
            rate = new Rate(idInstalacion, idUsuario, Calendar.getInstance().getTime(), estrellas, tiedComentario.getText().toString());
            presenter.add(rate);
        } else {
            Rate editableRate = new Rate(idInstalacion, idUsuario, rate.getFecha(), estrellas, tiedComentario.getText().toString());
            presenter.edit(editableRate);
        }
    }

    public interface OnRateSavedChanges {
        void onRateSavedChanges();
    }

    private class OnStarClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivStar1:
                    estrellas = 1;
                    ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar2.setImageResource(R.drawable.ic_no_fav_32dp);
                    ivStar3.setImageResource(R.drawable.ic_no_fav_32dp);
                    ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
                    ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                    break;
                case R.id.ivStar2:
                    estrellas = 2;
                    ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar3.setImageResource(R.drawable.ic_no_fav_32dp);
                    ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
                    ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                    break;
                case R.id.ivStar3:
                    estrellas = 3;
                    ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
                    ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                    break;
                case R.id.ivStar4:
                    estrellas = 4;
                    ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar4.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                    break;
                case R.id.ivStar5:
                    estrellas = 5;
                    ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar4.setImageResource(R.drawable.ic_fav_32dp);
                    ivStar5.setImageResource(R.drawable.ic_fav_32dp);
                    break;
            }
        }
    }
}
