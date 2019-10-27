package com.bilalmoreno.malagasport.data.service.listener;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Machine;
import com.bilalmoreno.malagasport.data.db.model.Workout;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;
import com.bilalmoreno.malagasport.data.repository.MachineRepository;
import com.bilalmoreno.malagasport.data.service.model.workout.Feature;
import com.bilalmoreno.malagasport.data.service.model.workout.Workouts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class WorkoutsCallback extends Callback implements retrofit2.Callback<Workouts> {
    private OnDownloadErrorListener listener;
    private static AsyncTask<Void, Void, Void> asyncTask;

    public WorkoutsCallback(OnDownloadErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<Workouts> call, final Response<Workouts> response) {
        final ArrayList<Workout> workouts = new ArrayList<>();
        final ArrayList<Machine> machines = new ArrayList<>();

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Workouts workoutsBody = response.body();
                for (Feature feature :
                        workoutsBody.getFeatures()) {
                    Workout workout = new Workout(Integer.valueOf(feature.getProperties().getIdZonamusculacion()),
                            feature.getProperties().getNomzonamusculacion(),
                            feature.getProperties().getUbicacion()
                    );
                    workout.setLatitude(feature.getGeometry().getCoordinates().get(1));
                    workout.setLongitude(feature.getGeometry().getCoordinates().get(0));

                    workouts.add(workout);

                    Machine machine = new Machine(Integer.valueOf(feature.getProperties().getIdMaquina()),
                            feature.getProperties().getNommaquina(),
                            Integer.valueOf(feature.getProperties().getNivel()),
                            feature.getProperties().getFuncion(),
                            feature.getProperties().getDesarrollo(),
                            feature.getProperties().getPrecauciones()
                    );
                    machine.setWorkout(workout.getId());

                    machines.add(machine);
                }
            return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (InstallationRepository.getInstance().updateWorkouts(workouts)) {
                    MachineRepository.getInstance().update(machines);
                }
            }
        }//.execute();
        ;
    }

    @Override
    public void onFailure(Call<Workouts> call, Throwable t) {
        listener.onDownloadError();
    }
}
