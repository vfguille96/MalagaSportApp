package com.bilalmoreno.malagasport.ui.rate;

import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.ui.base.BaseView;

public interface RateContract {
    interface View extends BaseView {
        void showRate(Rate rate);

        void setCommentEmptyError();

        void onSuccess();
    }

    interface Presenter {
        void load(int installationId);

        void load(String userId, int installationId);

        void add(Rate rate);

        void edit(Rate rate);

        void delete(Rate rate);
    }
}
