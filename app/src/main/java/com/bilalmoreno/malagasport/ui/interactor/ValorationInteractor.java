package com.bilalmoreno.malagasport.ui.interactor;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.data.db.repository.ValorationRepository;

import java.util.ArrayList;

public class ValorationInteractor {

    private OnLoadFinishedListener loadFinishedListener;
    private ValorationInteractorListener listener;
    private OnValorationDeleteListener valorationDeleteListener;

    public ValorationInteractor(ValorationInteractorListener listener, OnLoadFinishedListener loadFinishedListener, OnValorationDeleteListener valorationDeleteListener) {
        this.loadFinishedListener = loadFinishedListener;
        this.valorationDeleteListener = valorationDeleteListener;
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

    public void deleteValoration(final Valoration valoration) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ValorationRepository.getRepository().delete(valoration);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    valorationDeleteListener.onSuccess();
                } else {
                    valorationDeleteListener.onError(R.string.msg_error_valoration_delete);
                }
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

    public interface OnLoadFinishedListener extends OnErrorListener {
        void onSuccess(Valoration valoration);

        void onSuccess(ArrayList<Valoration> valorations);
    }

    public interface ValorationInteractorListener {
        void onCommentEmptyError();

        void onZeroStarsError();

        void onSavedChanges();
    }

    public interface OnValorationDeleteListener extends OnErrorListener {
        void onSuccess();
    }

    private interface OnErrorListener {
        void onError(int resourceErrorMessage);
    }
}
