package com.bilalmoreno.malagasport.ui.Login;

import com.bilalmoreno.malagasport.ui.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView {
        void setNameEmptyError();

        void setEmailEmptyError();

        void setInvalidEmailError();

        void setPasswordEmptyError();

        void setPasswordInvalidError();

        void setPasswordRepeatError();

        void setBirthDateEmptyError();

        void onRegisterSucess();

        void hideNameError();

        void hideEmailError();

        void hidePasswordError();

        void hidePasswordRepeatError();

        void hideDateError();

        void setRegisterFailedError();
    }

    interface Presenter {
        void validateRegister(String nombre, String email, String password, String passwordRepeat, String birthDate);
    }
}
