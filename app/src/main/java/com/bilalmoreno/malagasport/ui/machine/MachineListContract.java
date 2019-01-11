package com.bilalmoreno.malagasport.ui.machine;

import com.bilalmoreno.malagasport.data.db.model.Machine;
import com.bilalmoreno.malagasport.ui.base.BaseView;

import java.util.ArrayList;

public interface MachineListContract {
    interface View extends BaseView {
        void showMachines(ArrayList<Machine> machines);
    }

    interface Presenter {
        void load(int installationId);
    }
}
