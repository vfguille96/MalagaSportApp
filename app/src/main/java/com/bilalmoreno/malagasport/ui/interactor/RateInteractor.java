package com.bilalmoreno.malagasport.ui.interactor;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.data.repository.RateRepository;

import java.util.ArrayList;

public class RateInteractor {

    private OnLoadFinishedListener loadFinishedListener;
    private RateInteractorListener listener;
    private OnRateDeleteListener rateDeleteListener;

    public RateInteractor(RateInteractorListener listener, OnLoadFinishedListener loadFinishedListener, OnRateDeleteListener rateDeleteListener) {
        this.loadFinishedListener = loadFinishedListener;
        this.rateDeleteListener = rateDeleteListener;
        this.listener = listener;
    }

    public void addRate(Rate rate) {
        if (validate(rate.getEstrellas(), rate.getComentario())) {
            RateRepository.getInstance().add(rate);
            listener.onSavedChanges();
        }
    }

    public void editRate(Rate rate) {
        if (validate(rate.getEstrellas(), rate.getComentario())) {
            RateRepository.getInstance().edit(rate);
            listener.onSavedChanges();
        }
    }

    public void load(final String userId, final int installationId) {
        new AsyncTask<Void, Void, Rate>() {
            @Override
            protected Rate doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return RateRepository.getInstance().getRate(userId, installationId);
            }

            @Override
            protected void onPostExecute(Rate rate) {
                loadFinishedListener.onSuccess(rate);
            }
        }.execute();
    }

    public void load(final int installationId) {
        new AsyncTask<Void, Void, ArrayList<Rate>>() {
            @Override
            protected ArrayList<Rate> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return RateRepository.getInstance().getRates(installationId);
            }

            @Override
            protected void onPostExecute(ArrayList<Rate> rates) {
                loadFinishedListener.onSuccess(rates);
            }
        }.execute();
    }

    public void deleteRate(final Rate rate) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return RateRepository.getInstance().delete(rate);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    rateDeleteListener.onSuccess();
                } else {
                    rateDeleteListener.onError(R.string.msg_error_valoration_delete);
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
        void onSuccess(Rate rate);

        void onSuccess(ArrayList<Rate> rates);
    }

    public interface RateInteractorListener {
        void onCommentEmptyError();

        void onZeroStarsError();

        void onSavedChanges();
    }

    public interface OnRateDeleteListener extends OnErrorListener {
        void onSuccess();
    }

    private interface OnErrorListener {
        void onError(int resourceErrorMessage);
    }
}
