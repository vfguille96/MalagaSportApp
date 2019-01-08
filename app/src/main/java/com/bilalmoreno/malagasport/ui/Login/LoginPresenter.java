package com.bilalmoreno.malagasport.ui.Login;

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.LoginInteractorListener {

    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    @Override
    public void validateCredentials(String user, String password) {
        interactor.validate(user, password);
    }

    @Override
    public void onEmailEmptyError() {
        view.setEmailEmptyError();
    }

    @Override
    public void onInvalidEmailError() {
        view.setInvalidEmailError();
    }

    @Override
    public void onValidEmail() {
        view.hideEmailError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.setPasswordEmptyError();
    }

    @Override
    public void onValidPassword() {
        view.hidePasswordError();
    }

    @Override
    public void onAuthenticationError() {
        view.setAuthenticationFailedError();
    }

    @Override
    public void onAuthenticationSucess() {
        view.onAuthenticationSucess();
    }
}
