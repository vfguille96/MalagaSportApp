package com.bilalmoreno.malagasport.ui.workout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.adapter.WorkoutAdapter;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;

import java.util.ArrayList;

public class WorkoutListFragment extends BaseFragment implements WorkoutListContract.View, WorkoutAdapter.WorkoutListListener {

    public static final String TAG = "WorkoutListFragment";
    RecyclerView rvWorkout;
    Intent intent;
    private WorkoutAdapter adapter;
    private PrimaryActionButton primaryActionButton;
    private OnWorkoutShow callback;
    private WorkoutListContract.Presenter presenter;

    public static WorkoutListFragment getInstance(@Nullable Bundle bundle, @Nullable PrimaryActionButton primaryActionButton) {
        WorkoutListFragment fragment = new WorkoutListFragment();
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
            callback = (OnWorkoutShow) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnInstallationShow");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_list, container, false);
        if (rootView != null) {
            rvWorkout = rootView.findViewById(R.id.rvWorkout);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new WorkoutAdapter(getActivity(), this);
        rvWorkout.setAdapter(adapter);
        rvWorkout.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter = new WorkoutListPresenter(this);
        presenter.load();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_workout_list, menu);
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
            case R.id.action_menu_sort_machines_count_ascending:
                adapter.sort(new Installation.OrdenMaquinasCountAscendente());
                break;
            case R.id.action_menu_sort_machines_count_descending:
                adapter.sort(new Installation.OrdenMaquinasCountDescendente());
                break;
            default:
                return false;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onClick(View view) {
        callback.onWorkoutMachinesShow(adapter.getItem(rvWorkout.getChildAdapterPosition(view)).getId());
    }

    @Override
    public void showWorkouts(ArrayList<Installation> workouts) {
        adapter.clear();
        adapter.addAll(workouts);
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    public interface OnWorkoutShow {
        void onWorkoutMachinesShow(int installationId);
    }
}
