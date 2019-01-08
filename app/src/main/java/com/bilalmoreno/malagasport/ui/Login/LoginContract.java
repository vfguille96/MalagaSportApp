package com.bilalmoreno.malagasport.ui.Login;

import com.bilalmoreno.malagasport.ui.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void setEmailEmptyError();

        void setInvalidEmailError();

        void setPasswordEmptyError();

        void setAuthenticationFailedError();

        void onAuthenticationSucess();

        void hideEmailError();

        void hidePasswordError();
    }

    interface Presenter {
        void validateCredentials(String user, String password);
    }
}
