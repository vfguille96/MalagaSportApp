package com.bilalmoreno.malagasport.ui.installation;

import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.ui.base.BaseView;

public interface ValorationContract {
    interface View extends BaseView {
        void showValoration(Valoration valoration);

        void setCommentEmptyError();

        void onSuccess();
    }

    interface Presenter {
        void load(int installationId);

        void load(String userId, int installationId);

        void add(Valoration valoration);

        void edit(Valoration valoration);
    }
}
