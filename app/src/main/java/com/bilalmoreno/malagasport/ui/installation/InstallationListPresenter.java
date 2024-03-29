package com.bilalmoreno.malagasport.ui.installation;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.interactor.InstallationListInteractor;

import java.util.ArrayList;

public class InstallationListPresenter implements InstallationListContract.Presenter, InstallationListInteractor.OnLoadFinishedListener {

    private InstallationListContract.View view;
    private InstallationListInteractor interactor;


    public InstallationListPresenter(InstallationListContract.View view) {
        this.view = view;
        interactor = new InstallationListInteractor(this);
    }

    @Override
    public void load() {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void finish() {
        interactor.finish();
        view.hideProgress();
    }

    @Override
    public void onSuccess(ArrayList<Installation> installations) {
        view.hideProgress();
        view.showInstallations(installations);
    }

    @Override
    public void onError(int resourceErrorMessage) {

    }
}
