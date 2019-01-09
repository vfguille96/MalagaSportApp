package com.bilalmoreno.malagasport.ui.installation;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.data.db.repository.InstallationRepository;
import com.bilalmoreno.malagasport.data.db.repository.ValorationRepository;

import java.util.ArrayList;

public class InstallationInteractor {

    private OnLoadFinishedListener onLoadFinishedListener;

    public InstallationInteractor(OnLoadFinishedListener onLoadFinishedListener) {
        this.onLoadFinishedListener = onLoadFinishedListener;
    }

    public void load(final int installationId) {
        new AsyncTask<Void, Void, Installation>() {

            private ArrayList<Valoration> valorations;

            @Override
            protected Installation doInBackground(Void... voids) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                valorations = ValorationRepository.getRepository().getValoraciones(installationId);
                return InstallationRepository.getRepository().getInstalacion(installationId);
            }

            @Override
            protected void onPostExecute(final Installation installation) {
                onLoadFinishedListener.onSuccess(installation, valorations);
            }
        }.execute();
    }

    public boolean userHasRated(int installationId) {
        return ValorationRepository.getRepository().userHasRated(installationId);
    }

    public interface OnLoadFinishedListener {
        void onSuccess(Installation installation, ArrayList<Valoration> valorations);

        void onError(int resourceErrorMessage);
    }
}
