package com.bilalmoreno.malagasport.ui.installation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.ui.adapter.RateAdapter;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;

public class InstallationFragment extends BaseFragment implements InstallationContract.View, View.OnClickListener {

    RateAdapter adapter;

    TextView tvDireccion;

    ImageView ivMovRed;

    ImageView ivIluminacion;

    ListView lvRates;
    private PrimaryActionButton primaryActionButton;
    private InstallationContract.Presenter presenter;
    private OnRateShow callback;
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
            callback = (OnRateShow) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnInstallationShow");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instalacion, container, false);
        if (rootView != null) {
            tvDireccion = rootView.findViewById(R.id.tvDireccion);
            ivMovRed = rootView.findViewById(R.id.ivMovRed);
            ivIluminacion = rootView.findViewById(R.id.ivIluminacion);
            lvRates = rootView.findViewById(R.id.lvValoraciones);
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

                adapter = new RateAdapter(getContext());
                lvRates.setAdapter(adapter);
                lvRates.setOnItemClickListener(listener);


                presenter = new InstallationPresenter(this);
                presenter.load(idInstalacion);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_installation, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_sort_after_first:
                adapter.sort(new Rate.OrdenFechaDescendente());
                break;
            case R.id.action_menu_sort_before_first:
                adapter.sort(new Rate.OrdenFechaAscendente());
                break;
            default:
                return false;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!presenter.userHasRated(idInstalacion)) {
            primaryActionButton.onPrimaryActionButtonShow(R.drawable.ic_menu_add, this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        primaryActionButton.onPrimaryActionButtonHide();
    }

    @Override
    public void onClick(View v) {
        callback.onRateAdd(MalagaSportApplication.getUserId(), idInstalacion);
    }

    @Override
    public void showInstallation(Installation installation, ArrayList<Rate> rates) {
        tvDireccion.setText(installation.getDireccion());
        if (installation.getIluminacion()) {
            ivIluminacion.setVisibility(View.VISIBLE);
        }
        if (installation.getAccesoMovReducida()) {
            ivMovRed.setVisibility(View.VISIBLE);
        }
        adapter.clear();
        Collections.sort(rates, new Rate.OrdenFechaDescendente());
        adapter.addAll(rates);
    }

    public interface OnRateShow {
        void onRateEdit(String userId, int installationId);

        void onRateAdd(String userId, int idInstalacion);
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Rate rate = adapter.getItem(position);
            if (rate.getIdUsuario().equals(MalagaSportApplication.getUserId())) {
                callback.onRateEdit(rate.getIdUsuario(), rate.getIdInstlacion());
            }
        }
    }


}
