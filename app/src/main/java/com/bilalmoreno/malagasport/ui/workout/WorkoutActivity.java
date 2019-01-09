package com.bilalmoreno.malagasport.ui.workout;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.MainNavigationActivity;
import com.bilalmoreno.malagasport.ui.installation.InstallationListFragment;

public class WorkoutActivity extends MainNavigationActivity implements MachineListFragment.OnMachineShow, WorkoutListFragment.OnWorkoutShow {

    WorkoutListFragment workoutListFragment;
    MachineListFragment machineListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        workoutListFragment = (WorkoutListFragment) getSupportFragmentManager().findFragmentByTag(WorkoutListFragment.TAG);

        if (workoutListFragment == null) {
            workoutListFragment = WorkoutListFragment.getInstance(null, this);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.content, workoutListFragment, InstallationListFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onMachineShow(int installationId) {
        Toast.makeText(this, String.valueOf(installationId), Toast.LENGTH_SHORT).show();
        //TODO mostrar info maquina (MachineFragment)
    }

    @Override
    public void onWorkoutMachinesShow(int installationId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Installation.TAG, installationId);

        machineListFragment = MachineListFragment.getInstance(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, machineListFragment, MachineListFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
