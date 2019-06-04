package com.bilalmoreno.malagasport.ui.interactor;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;

import java.util.ArrayList;

public class InstallationListInteractor {

    private OnLoadFinishedListener loadFinishedListener;
    private AsyncTask<Void, Void, ArrayList<Installation>> load;

    public InstallationListInteractor(OnLoadFinishedListener loadFinishedListener) {
        this.loadFinishedListener = loadFinishedListener;
    }

    public void load() {
        load = new AsyncTask<Void, Void, ArrayList<Installation>>() {

            @Override
            protected ArrayList<Installation> doInBackground(Void... voids) {
                return InstallationRepository.getInstance().getInstallations();
            }

            @Override
            protected void onPostExecute(ArrayList<Installation> installations) {
                loadFinishedListener.onSuccess(installations);
            }
        };
        load.execute();
    }

    public void finish() {
        load.cancel(true);
    }

    public interface OnLoadFinishedListener {
        void onSuccess(ArrayList<Installation> installations);

        void onError(int resourceErrorMessage);
    }
}
