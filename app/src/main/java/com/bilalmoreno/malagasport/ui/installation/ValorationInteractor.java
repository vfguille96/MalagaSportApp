package com.bilalmoreno.malagasport.ui.installation;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.data.db.repository.ValorationRepository;

import java.util.ArrayList;

class ValorationInteractor {

    private OnLoadFinishedListener loadFinishedListener;
    private ValorationInteractorListener listener;

    public ValorationInteractor(OnLoadFinishedListener loadFinishedListener, ValorationInteractorListener listener) {
        this.loadFinishedListener = loadFinishedListener;
        this.listener = listener;
    }

    public void addValoration(Valoration valoration) {
        if (validate(valoration.getEstrellas(), valoration.getComentario())) {
            ValorationRepository.getRepository().add(valoration);
            listener.onSavedChanges();
        }
    }

    public void editValoration(Valoration valoration) {
        if (validate(valoration.getEstrellas(), valoration.getComentario())) {
            ValorationRepository.getRepository().edit(valoration);
            listener.onSavedChanges();
        }
    }

    public void load(final String userId, final int installationId) {
        new AsyncTask<Void, Void, Valoration>() {
            @Override
            protected Valoration doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ValorationRepository.getRepository().getValoracion(userId, installationId);
            }

            @Override
            protected void onPostExecute(Valoration valoration) {
                loadFinishedListener.onSuccess(valoration);
            }
        }.execute();
    }

    public void load(final int installationId) {
        new AsyncTask<Void, Void, ArrayList<Valoration>>() {
            @Override
            protected ArrayList<Valoration> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ValorationRepository.getRepository().getValoraciones(installationId);
            }

            @Override
            protected void onPostExecute(ArrayList<Valoration> valorations) {
                loadFinishedListener.onSuccess(valorations);
            }
        }.execute();
    }

    private boolean validate(int estrellas, String comentario) {
        return validarEstrellas(estrellas) && validarComentario(comentario);
    }

    private boolean validarEstrellas(int estrellas) {
        if (estrellas <= 0 || estrellas > 5) {
            listener.onZeroStarsError();
            return false;
        }
        return true;
    }

    private boolean validarComentario(String comentario) {
        if (comentario.isEmpty()) {
            listener.onCommentEmptyError();
            return false;
        }
        return true;
    }

    public interface OnLoadFinishedListener {
        void onSuccess(Valoration valoration);

        void onSuccess(ArrayList<Valoration> valorations);

        void onError(int resourceErrorMessage);
    }

    public interface ValorationInteractorListener {
        void onCommentEmptyError();

        void onZeroStarsError();

        void onSavedChanges();
    }
}
