package com.bilalmoreno.malagasport.ui.installation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.adapter.InstallationAdapter;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;

public class InstallationListFragment extends BaseFragment implements InstallationListContract.View, InstallationAdapter.InstallationListListener {

    public static final String TAG = "InstallationListFragment";
    private RecyclerView rvInstallations;
    private InstallationAdapter adapter;
    private OnInstallationShow callback;
    private InstallationListContract.Presenter presenter;
    private PrimaryActionButton primaryActionButton;

    public static Fragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
        InstallationListFragment fragment = new InstallationListFragment();
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
            callback = (OnInstallationShow) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnInstallationShow");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.finish();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instalacion_list, container, false);
        if (rootView != null) {
            rvInstallations = rootView.findViewById(R.id.lvInstalaciones);

        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new InstallationListPresenter(this);
        presenter.load();
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.finish();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new InstallationAdapter(getContext(), this);
        rvInstallations.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInstallations.setAdapter(adapter);

//        primaryActionButton.onPrimaryActionButtonShow(R.drawable.ic_menu_send);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_installation_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_sort_az:
                adapter.sort(new Installation.OrdenAlfabeticoAscendente());
                break;
            case R.id.action_menu_sort_za:
                adapter.sort(new Installation.OrdenAlfabeticoDescendente());
                break;
            default:
                return false;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onClick(View view) {
        callback.onInstallationShow(adapter.getItem(rvInstallations.getChildAdapterPosition(view)).getId());
    }

    @Override
    public void showInstallations(ArrayList<Installation> installations) {
        Collections.sort(installations, new Installation.OrdenAlfabeticoAscendente());
        adapter.clear();
        adapter.addAll(installations);
        adapter.notifyDataSetChanged();
    }

    public interface OnInstallationShow {
        void onInstallationShow(int installationId);
    }
}
