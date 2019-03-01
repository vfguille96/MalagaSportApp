package com.bilalmoreno.malagasport.ui.installation;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.ui.base.BaseView;

import java.util.ArrayList;

public interface InstallationContract {
    interface View extends BaseView {
        void showInstallation(Installation installation, ArrayList<Rate> rates);
    }

    interface Presenter {
        void load(int installationId);

        boolean userHasRated(int installationId);
    }
}
