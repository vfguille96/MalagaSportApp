package com.bilalmoreno.malagasport.ui.installation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bilalmoreno.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.ui.adapter.ValoracionAdapter;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.ArrayList;

public class InstallationFragment extends BaseFragment implements InstallationContract.View, View.OnClickListener {

    ValoracionAdapter adapter;

    TextView tvDireccion;

    ImageView ivMovRed;

    ImageView ivIluminacion;

    ListView lvValoraciones;
    private PrimaryActionButton primaryActionButton;
    private InstallationContract.Presenter presenter;
    private OnValorationShow callback;
    private OnItemClickListener listener;
    private int idInstalacion;


    public static InstallationFragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
        InstallationFragment fragment = new InstallationFragment();
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
            callback = (OnValorationShow) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnInstallationShow");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_instalacion, container, false);
        if (rootView != null) {
            tvDireccion = rootView.findViewById(R.id.tvDireccion);
            ivMovRed = rootView.findViewById(R.id.ivMovRed);
            ivIluminacion = rootView.findViewById(R.id.ivIluminacion);
            lvValoraciones = rootView.findViewById(R.id.lvValoraciones);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            idInstalacion = bundle.getInt(Installation.TAG, -1);
            if (idInstalacion != -1) {

                listener = new OnItemClickListener();

                adapter = new ValoracionAdapter(getContext());
                lvValoraciones.setAdapter(adapter);
                lvValoraciones.setOnItemClickListener(listener);


                presenter = new InstallationPresenter(this);
                presenter.load(idInstalacion);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!presenter.userHasRated(idInstalacion)) {
            primaryActionButton.onPrimaryActionButtonShow(R.drawable.ic_menu_share, this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        primaryActionButton.onPrimaryActionButtonHide();
    }

    @Override
    public void onClick(View v) {
        callback.onValorationShow(ValoracionFragment.VALORACION_ADD, MalagaSportApplication.getUserId(), idInstalacion);
    }

    @Override
    public void showInstallation(Installation installation, ArrayList<Valoration> valorations) {
        tvDireccion.setText(installation.getDireccion());
        if (installation.getIluminacion()) {
            ivIluminacion.setVisibility(View.VISIBLE);
        }
        if (installation.getAccesoMovReducida()) {
            ivMovRed.setVisibility(View.VISIBLE);
        }
        adapter.clear();
        adapter.addAll(valorations);
    }

    public interface OnValorationShow {
        void onValorationShow(int action, String userId, int installationId);

    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Valoration valoration = adapter.getItem(position);
            callback.onValorationShow(ValoracionFragment.VALORACION_EDIT, valoration.getIdUsuario(), valoration.getIdInstlacion());
        }
    }


}
