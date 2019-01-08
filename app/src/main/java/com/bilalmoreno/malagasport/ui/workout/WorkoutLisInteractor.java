package com.bilalmoreno.malagasport.ui.workout;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.repository.InstallationRepository;

import java.util.ArrayList;

public class WorkoutLisInteractor {

    private OnLoadFinishedListener loadFinishedListener;

    public WorkoutLisInteractor(OnLoadFinishedListener loadFinishedListener) {
        this.loadFinishedListener = loadFinishedListener;
    }

    void load() {
        new AsyncTask<Void, Void, ArrayList<Installation>>() {
            @Override
            protected ArrayList<Installation> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return InstallationRepository.getRepository().getWorkout();
            }

            @Override
            protected void onPostExecute(ArrayList<Installation> installations) {
                loadFinishedListener.onSuccess(installations);
            }
        }.execute();
    }

    public interface OnLoadFinishedListener {
        void onSuccess(ArrayList<Installation> installations);

        void onError(int resourceErrorMessage);
    }
}
