package com.bilalmoreno.malagasport.ui.interactor;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;
import com.bilalmoreno.malagasport.data.repository.RateRepository;

import java.util.ArrayList;

public class InstallationInteractor {

    private OnLoadFinishedListener onLoadFinishedListener;

    public InstallationInteractor(OnLoadFinishedListener onLoadFinishedListener) {
        this.onLoadFinishedListener = onLoadFinishedListener;
    }

    public void load(final int installationId) {
        new AsyncTask<Void, Void, Installation>() {

            private ArrayList<Rate> rates;

            @Override
            protected Installation doInBackground(Void... voids) {
                rates = RateRepository.getInstance().getRates(installationId);
                return InstallationRepository.getInstance().getInstalacion(installationId);
            }

            @Override
            protected void onPostExecute(final Installation installation) {
                onLoadFinishedListener.onSuccess(installation, rates);
            }
        }.execute();
    }

    public boolean userHasRated(int installationId) {
        return RateRepository.getInstance().userHasRated(installationId);
    }

    public interface OnLoadFinishedListener {
        void onSuccess(Installation installation, ArrayList<Rate> rates);

        void onError(int resourceErrorMessage);
    }
}
