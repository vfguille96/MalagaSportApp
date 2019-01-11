package com.bilalmoreno.malagasport.ui.splash;


import com.bilalmoreno.malagasport.ui.interactor.UserInteractor;

public class SplashPresenter implements SplashContract.Presenter, UserInteractor.AuthenticationListener {

    UserInteractor interactor;
    private SplashContract.View view;

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
