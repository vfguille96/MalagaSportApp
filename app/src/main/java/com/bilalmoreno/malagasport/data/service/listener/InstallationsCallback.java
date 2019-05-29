package com.bilalmoreno.malagasport.data.service.listener;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;
import com.bilalmoreno.malagasport.data.service.model.installation.Feature;
import com.bilalmoreno.malagasport.data.service.model.installation.Installations;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class InstallationsCallback extends Callback implements retrofit2.Callback<Installations> {
    private OnDownloadErrorListener listener;

    public InstallationsCallback(OnDownloadErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(final Call<Installations> call, final Response<Installations> response) {
// Tratar contenido e insertar en la base de datos
        final ArrayList<Installation> add = new ArrayList<>();
        final ArrayList<Installation> update = new ArrayList<>();

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Installations installations = response.body();
                for (Feature feature :
                        installations.getFeatures()) {
                    int id = feature.getProperties().getID();
                    String name = feature.getProperties().getNOMBRE();
                    String address = feature.getProperties().getDIRECCION();
                    Double latitude = feature.getGeometry().getCoordinates().get(1);
                    Double longitude = feature.getGeometry().getCoordinates().get(0);
                    boolean redMovility = feature.getProperties().getACCESOPMR().equalsIgnoreCase("Si");
                    boolean youngCard = feature.getProperties().getTARJETAJOVEN().equalsIgnoreCase("Si");
                    String description = feature.getProperties().getDESCRIPCION();
                    String web = feature.getProperties().getURL();
                    String email = feature.getProperties().getEMAIL();
                    String phone = feature.getProperties().getCONTACTO();
                    String schedule = feature.getProperties().getHORARIOS();
                    String price = feature.getProperties().getPRECIOS();

                    Installation installation = new Installation(id, name, address, latitude, longitude, youngCard, redMovility);
                    installation.setDescripcion(description);
                    installation.setWeb(web);
                    installation.setEmail(email);
                    installation.setTelefono(phone);
                    installation.setHorario(schedule);
                    installation.setPrecio(price);

                    if (InstallationRepository.getInstance().getInstalacion(id) == null) {
                        add.add(installation);
                    } else {
                        update.add(installation);
                    }

                    //TODO Insertar pistas deportivas o Tracks (aún sin uso en el prototipo)
                    //            for (INFOESP infoEsp :
                    //                    feature.getProperties().getINFOESP()) {
                    //                String sportSpace = infoEsp.getEspacioDeportivo();
                    //
                    //            }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                InstallationRepository.getInstance().addInstallations(add);
                InstallationRepository.getInstance().updateInstallations(update);
                listener.onCallFinish();
            }
        }.execute();
    }

    @Override
    public void onFailure(Call<Installations> call, Throwable t) {
        listener.onDownloadError();
    }
}
