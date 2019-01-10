package com.bilalmoreno.malagasport.ui.login;

public class RegisterPresenter implements RegisterContract.Presenter, UserInteractor.RegisterListener {

    private UserInteractor interactor;
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        interactor = new UserInteractor(this);
    }

    @Override
    public void validateRegister(String nombre, String email, String password, String passwordRepeat, String birthDate) {
        view.showProgress();
        interactor.validateRegister(nombre, email, password, passwordRepeat, birthDate);
    }

    @Override
    public void onNameEmptyError() {
        view.hideProgress();
        view.setNameEmptyError();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.setEmailEmptyError();
    }

    @Override
    public void onInvalidEmailError() {
        view.hideProgress();
        view.setInvalidEmailError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onInvalidPasswordError() {
        view.hideProgress();
        view.setPasswordInvalidError();
    }

    @Override
    public void onPasswordRepeatError() {
        view.hideProgress();
        view.setPasswordRepeatError();
    }

    @Override
    public void onBirthDateEmptyError() {
        view.hideProgress();
        view.setBirthDateEmptyError();
    }

    @Override
    public void onRegisterSuccess() {
        view.hideProgress();
        view.onRegisterSucess();
    }

    @Override
    public void onValidName() {
        view.hideProgress();
        view.hideNameError();
    }

    @Override
    public void onValidEmail() {
        view.hideProgress();
        view.hideEmailError();
    }

    @Override
    public void onValidPassword() {
        view.hideProgress();
        view.hidePasswordError();
    }

    @Override
    public void onValidPasswordRepeat() {
        view.hideProgress();
        view.hidePasswordRepeatError();
    }

    @Override
    public void onValidDate() {
        view.hideProgress();
        view.hideDateError();
    }

    @Override
    public void onRegisterFailed() {
        view.hideProgress();
        view.setRegisterFailedError();
    }
}