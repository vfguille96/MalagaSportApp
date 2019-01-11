package com.bilalmoreno.malagasport.ui.machine;

import com.bilalmoreno.malagasport.data.db.model.Machine;
import com.bilalmoreno.malagasport.ui.interactor.MachineListInteractor;

import java.util.ArrayList;

public class MachineListPresenter implements MachineListContract.Presenter, MachineListInteractor.OnLoadFinishedListener {

    MachineListContract.View view;
    MachineListInteractor interactor;

    public MachineListPresenter(MachineListContract.View view) {
        this.view = view;
        interactor = new MachineListInteractor(this);
    }

    @Override
    public void load(int installationId) {
        view.showProgress();
        interactor.load(installationId);
    }

    @Override
    public void onSuccess(ArrayList<Machine> machines) {
        view.hideProgress();
        view.showMachines(machines);
    }

    @Override
    public void onError(int resourceErrorMessage) {
        view.hideProgress();
        view.showError(resourceErrorMessage);
    }
}
