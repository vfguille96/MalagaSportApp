package com.bilalmoreno.malagasport.ui.Login;

import com.bilalmoreno.MalagaSportApplication;
import com.bilalmoreno.malagasport.data.db.repository.UsuarioRepository;

public class LoginInteractor {

    LoginInteractorListener listener;

    public LoginInteractor(LoginInteractorListener listener) {
        this.listener = listener;
    }

    public void validate(String user, String password) {
        if (validarEmail(user) & validarPassword(password)) {
            validarCredenciales(user, password);
        }
    }

    private boolean validarEmail(String email) {
        if (email.isEmpty()) {
            listener.onEmailEmptyError();
            return false;
        }
        if (!MalagaSportApplication.VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
            listener.onInvalidEmailError();
            return false;
        }
        listener.onValidEmail();
        return true;
    }

    private boolean validarPassword(String password) {
        if (password.isEmpty()) {
            listener.onPasswordEmptyError();
            return false;
        }
        listener.onValidPassword();
        return true;
    }

    private boolean validarCredenciales(String user, String password) {
        if (!UsuarioRepository.getRepository().validarCredenciales(user, password)) {
            listener.onAuthenticationError();
            return false;
        }
        listener.onAuthenticationSucess();
        return true;
    }

    public interface LoginInteractorListener {
        void onEmailEmptyError();

        void onInvalidEmailError();

        void onValidEmail();

        void onPasswordEmptyError();

        void onValidPassword();

        void onAuthenticationError();

        void onAuthenticationSucess();
    }
}
