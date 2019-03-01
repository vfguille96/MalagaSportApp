package com.bilalmoreno.malagasport.ui.workout;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Workout;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;

import java.util.ArrayList;

public class WorkoutLisInteractor {

    private OnLoadFinishedListener loadFinishedListener;

    public WorkoutLisInteractor(OnLoadFinishedListener loadFinishedListener) {
        this.loadFinishedListener = loadFinishedListener;
    }

    void load() {
        new AsyncTask<Void, Void, ArrayList<Workout>>() {
            @Override
            protected ArrayList<Workout> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return InstallationRepository.getInstance().getWorkouts();
            }

            @Override
            protected void onPostExecute(ArrayList<Workout> workouts) {
                loadFinishedListener.onSuccess(workouts);
            }
        }.execute();
    }

    public interface OnLoadFinishedListener {
        void onSuccess(ArrayList<Workout> installations);

        void onError(int resourceErrorMessage);
    }
}
