package com.bilalmoreno.malagasport.ui.splash;


import com.bilalmoreno.malagasport.ui.login.LoginContract;
import com.bilalmoreno.malagasport.ui.login.UserInteractor;

public class SplashPresenter implements SplashContract.Presenter, UserInteractor.AuthenticationListener {

    private SplashContract.View view;
    UserInteractor interactor;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
        interactor = new UserInteractor(this);
    }

    @Override
    public void validateCredentials(String user, String password) {
        interactor.validateCredentials(user, password);
    }

    @Override
    public void onAuthenticationSucess() {
        view.onAuthenticationSucess();
    }

    @Override
    public void onAuthenticationError() {
        view.onAuthenticationFailed();
    }
}
