package com.bilalmoreno.malagasport.ui.splash;

import com.bilalmoreno.malagasport.ui.login.LoginContract;

public interface SplashContract {
    interface View {
        void onAuthenticationFailed();

        void onAuthenticationSucess();
    }

    interface Presenter extends LoginContract.Presenter { }
}
