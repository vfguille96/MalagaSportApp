package com.bilalmoreno.malagasport.ui.installation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Usuario;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.ui.MainNavigationActivity;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.Calendar;

public class ValoracionFragment extends BaseFragment implements ValorationContract.View, View.OnClickListener {

    public static final int VALORACION_ADD = 0;
    public static final int VALORACION_EDIT = 1;
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
    private Valoration valoration;
    private String idUsuario;

    private ValorationContract.Presenter presenter;
    private OnValorationSavedChanges callback;
    private PrimaryActionButton primaryActionButton;
    private OnStarClickListener onStarClickListener;

    public static ValoracionFragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
        ValoracionFragment fragment = new ValoracionFragment();
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
            callback = (OnValorationSavedChanges) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnInstallationShow");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_valoracion, container, false);
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

        presenter = new ValorationPresenter(this);

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
            idUsuario = bundle.getString(Usuario.TAG);
            if (idUsuario != null && actionCode == VALORACION_EDIT && idInstalacion != -1) {
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
    public void showValoration(Valoration valoration) {
        if (actionCode == VALORACION_EDIT) {
            this.valoration = valoration;
            tiedComentario.setText(valoration.getComentario());
            estrellas = valoration.getEstrellas();
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
            this.valoration = null;
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
        callback.onValorationSavedChanges();
    }


    @Override
    public void onClick(View v) {
        ((MainNavigationActivity) getActivity()).hideKeyboard();
        if (actionCode == VALORACION_ADD) {
            valoration = new Valoration(idInstalacion, idUsuario, Calendar.getInstance().getTime(), estrellas, tiedComentario.getText().toString());
            presenter.add(valoration);
        } else {
            Valoration valorationEdit = new Valoration(idInstalacion, idUsuario, valoration.getFecha(), estrellas, tiedComentario.getText().toString());
            presenter.edit(valorationEdit);
        }
    }

    public interface OnValorationSavedChanges {
        void onValorationSavedChanges();
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
