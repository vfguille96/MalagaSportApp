package com.bilalmoreno.malagasport.ui.installation;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;

import java.util.ArrayList;

public class InstallationListInteractor {

    private OnLoadFinishedListener loadFinishedListener;

    public InstallationListInteractor(OnLoadFinishedListener loadFinishedListener) {
        this.loadFinishedListener = loadFinishedListener;
    }

    public void load() {
        new AsyncTask<Void, Void, ArrayList<Installation>>() {

            @Override
            protected ArrayList<Installation> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return InstallationRepository.getInstance().getInstallations();
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
