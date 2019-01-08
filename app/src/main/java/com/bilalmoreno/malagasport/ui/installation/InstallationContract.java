package com.bilalmoreno.malagasport.ui.installation;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.ui.base.BaseView;

import java.util.ArrayList;

public interface InstallationContract {
    interface View extends BaseView {
        void showInstallation(Installation installation, ArrayList<Valoration> valorations);
    }

    interface Presenter {
        void load(int installationId);

        boolean userHasRated(int installationId);
    }
}
