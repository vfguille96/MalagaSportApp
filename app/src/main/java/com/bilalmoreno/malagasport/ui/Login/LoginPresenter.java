package com.bilalmoreno.malagasport.ui.Login;

public class LoginPresenter implements LoginContract.Presenter, UserInteractor.LoginListener {

    private LoginContract.View view;
    private UserInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new UserInteractor(this);
    }

    @Override
    public void validateCredentials(String user, String password) {
        interactor.validateCredentials(user, password);
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
