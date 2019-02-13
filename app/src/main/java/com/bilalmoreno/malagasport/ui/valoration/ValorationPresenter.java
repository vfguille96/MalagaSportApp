package com.bilalmoreno.malagasport.ui.valoration;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.ui.interactor.ValorationInteractor;

import java.util.ArrayList;

public class ValorationPresenter implements ValorationContract.Presenter, ValorationInteractor.OnLoadFinishedListener, ValorationInteractor.ValorationInteractorListener, ValorationInteractor.OnValorationDeleteListener {

    private ValorationContract.View view;
    private ValorationInteractor interactor;

    public ValorationPresenter(ValorationContract.View view) {
        this.view = view;
        interactor = new ValorationInteractor(this, this, this);
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
    public void add(Valoration valoration) {
        view.showProgress();
        interactor.addValoration(valoration);
    }

    @Override
    public void edit(Valoration valoration) {
        view.showProgress();
        interactor.editValoration(valoration);
    }

    @Override
    public void delete(Valoration valoration) {
        view.showProgress();
        interactor.deleteValoration(valoration);
    }

    @Override
    public void onSuccess(Valoration valoration) {
        view.hideProgress();
        view.showValoration(valoration);
    }

    @Override
    public void onSuccess(ArrayList<Valoration> valorations) {
        view.hideProgress();
//        view.showValoration(valorations);
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
