package com.bilalmoreno.malagasport.ui.rate;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.ui.interactor.RateInteractor;

import java.util.ArrayList;

public class RatePresenter implements RateContract.Presenter, RateInteractor.OnLoadFinishedListener, RateInteractor.RateInteractorListener, RateInteractor.OnRateDeleteListener {

    private RateContract.View view;
    private RateInteractor interactor;

    public RatePresenter(RateContract.View view) {
        this.view = view;
        interactor = new RateInteractor(this, this, this);
    }

    @Override
    public void load(int installationId) {
        view.showProgress();
        interactor.load(installationId);
    }

    @Override
    public void load(String userId, int installationId) {
        view.showProgress();
        interactor.load(userId, installationId);
    }

    @Override
    public void add(Rate rate) {
        view.showProgress();
        interactor.addRate(rate);
    }

    @Override
    public void edit(Rate rate) {
        view.showProgress();
        interactor.editRate(rate);
    }

    @Override
    public void delete(Rate rate) {
        view.showProgress();
        interactor.deleteRate(rate);
    }

    @Override
    public void onSuccess(Rate rate) {
        view.hideProgress();
        view.showRate(rate);
    }

    @Override
    public void onSuccess(ArrayList<Rate> rates) {
        view.hideProgress();
//        view.showRate(rates);
        //No usado
    }

    @Override
    public void onError(int resourceErrorMessage) {
        view.hideProgress();
        view.showError(resourceErrorMessage);
    }

    @Override
    public void onCommentEmptyError() {
        view.hideProgress();
        view.setCommentEmptyError();
    }

    @Override
    public void onZeroStarsError() {
        view.hideProgress();
        view.showError(R.string.msg_err_valoracion);

    }

    @Override
    public void onSavedChanges() {
        view.hideProgress();
        view.onSuccess();
    }

    @Override
    public void onSuccess() {
        view.hideProgress();
        view.onSuccess();
    }
}
