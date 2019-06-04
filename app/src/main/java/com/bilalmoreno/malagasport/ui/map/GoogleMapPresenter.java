package com.bilalmoreno.malagasport.ui.map;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.ui.interactor.InstallationListInteractor;

import java.util.ArrayList;

public class GoogleMapPresenter implements GoogleMapContract.Presenter, InstallationListInteractor.OnLoadFinishedListener {
    private GoogleMapContract.View view;
    private InstallationListInteractor interactor;

    public GoogleMapPresenter(GoogleMapContract.View view) {
        this.view = view;
        interactor = new InstallationListInteractor(this);
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void onSuccess(ArrayList<Installation> installations) {
        view.show(installations);
    }

    @Override
    public void onError(int resourceErrorMessage) {

    }
}
