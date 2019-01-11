package com.bilalmoreno.malagasport.ui.recovery;

import com.bilalmoreno.malagasport.ui.interactor.UserInteractor;

public class RecoveryPresenter implements RecoveryContract.Presenter, UserInteractor.RecoveryListener {

    private UserInteractor interactor;
    private RecoveryContract.View view;

    public RecoveryPresenter(RecoveryContract.View view) {
        this.view = view;
        this.interactor = new UserInteractor(this);
    }

    @Override
    public void validateChangePassword(String email, String password, String passwordRepeat) {
        view.showProgress();
        interactor.validateChangePassword(email, password, passwordRepeat);
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
    public void onChangePasswordSuccess() {
        view.hideProgress();
        view.onChangePasswordSuccess();
    }

    @Override
    public void onChangePasswordFailed() {
        view.hideProgress();
        view.onChangePasswordFailed();
    }
}
