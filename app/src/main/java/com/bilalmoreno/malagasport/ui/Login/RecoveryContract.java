package com.bilalmoreno.malagasport.ui.Login;

import com.bilalmoreno.malagasport.ui.base.BaseView;

public interface RecoveryContract {
    interface View extends BaseView {
        void setEmailEmptyError();

        void setInvalidEmailError();

        void setPasswordEmptyError();

        void setPasswordInvalidError();

        void setPasswordRepeatError();

        void hideEmailError();

        void hidePasswordError();

        void hidePasswordRepeatError();

        void onChangePasswordSuccess();

        void onChangePasswordFailed();
    }

    interface Presenter {
        void validateChangePassword(String email, String password, String passwordRepeat);
    }
}
