package com.bilalmoreno.malagasport.ui.installation;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Valoration;

import java.util.ArrayList;

public class InstallationPresenter implements InstallationContract.Presenter, InstallationInteractor.OnLoadFinishedListener {

    private InstallationContract.View view;
    private InstallationInteractor interactor;

    public InstallationPresenter(InstallationContract.View view) {
        this.view = view;
        interactor = new InstallationInteractor(this);
    }

    @Override
    public void load(int installationId) {
        view.showProgress();
        interactor.load(installationId);
    }

    @Override
    public boolean userHasRated(int installationId) {
        return interactor.userHasRated(installationId);
    }

    @Override
    public void onSuccess(Installation installation, ArrayList<Valoration> valorations) {
        view.hideProgress();
        view.showInstallation(installation, valorations);
    }

    @Override
    public void onError(int resourceErrorMessage) {
        view.showError(resourceErrorMessage);
    }
}
