package com.bilalmoreno.malagasport.ui.installation;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.ui.base.BaseView;

import java.util.ArrayList;

public interface InstallationListContract {
    interface View extends BaseView {
        void showInstallations(ArrayList<Installation> installations);
    }

    interface Presenter {
        void load();

        void finish();
    }
}
