package com.bilalmoreno.malagasport.ui.workout;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Machine;
import com.bilalmoreno.malagasport.data.db.repository.InstallationRepository;
import com.bilalmoreno.malagasport.data.db.repository.MachineRepository;

import java.util.ArrayList;

public class MachineListInteractor {

    private OnLoadFinishedListener loadFinishedListener;

    public MachineListInteractor(OnLoadFinishedListener loadFinishedListener) {
        this.loadFinishedListener = loadFinishedListener;
    }

    void load(final int installationId) {
        new AsyncTask<Void, Void, ArrayList<Machine>>() {
            @Override
            protected ArrayList<Machine> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return MachineRepository.getRepository().getMaquinas(InstallationRepository.getRepository().getInstalacion(installationId).getMaquinas());
            }

            @Override
            protected void onPostExecute(ArrayList<Machine> machines) {
                loadFinishedListener.onSuccess(machines);
            }
        }.execute();
    }

    public interface OnLoadFinishedListener {
        void onSuccess(ArrayList<Machine> machines);

        void onError(int resourceErrorMessage);
    }
}
