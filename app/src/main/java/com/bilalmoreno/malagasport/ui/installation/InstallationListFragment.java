package com.bilalmoreno.malagasport.ui.installation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.adapter.InstallationAdapter;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.ArrayList;

public class InstallationListFragment extends BaseFragment implements InstallationListContract.View, InstallationAdapter.InstallationListListener {

    public static final String TAG = "InstallationListFragment";
    private RecyclerView rvInstallations;
    private InstallationAdapter adapter;
    private OnInstallationShow callback;
    private InstallationListContract.Presenter presenter;
    private PrimaryActionButton primaryActionButton;

    public static InstallationListFragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
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
    public void onClick(View view) {
        callback.onInstallationShow(adapter.getItem(rvInstallations.getChildAdapterPosition(view)).getId());
    }

    @Override
    public void showInstallations(ArrayList<Installation> installations) {
        adapter.clear();
        adapter.addAll(installations);
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_instalacion_list, container, false);
        if (rootView != null) {
            rvInstallations = rootView.findViewById(R.id.lvInstalaciones);

        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new InstallationAdapter(getContext(), this);
        rvInstallations.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInstallations.setAdapter(adapter);

//        primaryActionButton.onPrimaryActionButtonShow(R.drawable.ic_menu_send);

        presenter = new InstallationListPresenter(this);
        presenter.load();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        primaryActionButton.onPrimaryActionButtonHide();
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        primaryActionButton.onPrimaryActionButtonHide();
//    }

    public interface OnInstallationShow {
        void onInstallationShow(int installationId);
    }
}
