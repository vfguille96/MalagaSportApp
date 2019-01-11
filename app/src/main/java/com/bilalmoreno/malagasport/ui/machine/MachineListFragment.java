package com.bilalmoreno.malagasport.ui.machine;

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
import android.widget.ListView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Machine;
import com.bilalmoreno.malagasport.ui.adapter.MachineAdapter;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;

public class MachineListFragment extends BaseFragment implements MachineListContract.View, MachineAdapter.MachineListListener {

    public static final String TAG = "MachineListFragment";
    MachineAdapter adapter;
    int installationId;
    @BindView(R.id.lvMaquinas)
    ListView lvMaquinas;
    private OnMachineShow callback;
    private PrimaryActionButton primaryActionButton;
    private MachineListContract.Presenter presenter;

    public static MachineListFragment getInstance(@Nullable Bundle bundle) {
        MachineListFragment fragment = new MachineListFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    public MachineListFragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
        MachineListFragment fragment = new MachineListFragment();
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
            callback = (OnMachineShow) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnMachineShow");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_machine_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maquina_list, container, false);
        if (rootView != null) {
            lvMaquinas = rootView.findViewById(R.id.lvMaquinas);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            installationId = bundle.getInt(Installation.TAG, -1);
            if (installationId != -1) {
                adapter = new MachineAdapter(getContext(), this);
                lvMaquinas.setAdapter(adapter);
                presenter = new MachineListPresenter(this);
                presenter.load(installationId);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_sort_level_ascending:
                adapter.sort(new Machine.OrdenNivelAscendente());
                break;
            case R.id.action_menu_sort_level_descending:
                adapter.sort(new Machine.OrdenNivelDescendente());
                break;
            default:
                return false;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void showMachines(ArrayList<Machine> machines) {
        Collections.sort(machines, new Machine.OrdenNivelAscendente());
        adapter.clear();
        adapter.addAll(machines);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        callback.onMachineShow(((Machine) adapter.getItem(position)).getId());
    }

    public interface OnMachineShow {
        void onMachineShow(int installationId);
    }
}
