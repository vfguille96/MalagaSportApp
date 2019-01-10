package com.bilalmoreno.malagasport.ui.login;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.data.db.repository.UserRepository;

import java.text.ParseException;
import java.util.Calendar;

import static com.bilalmoreno.malagasport.MalagaSportApplication.MIN_PASSWORD_LENGTH;

public class UserInteractor {

    private AuthenticationListener splashListener;
    private LoginListener loginListener;
    private RegisterListener registerListener;
    private RecoveryListener recoveryListener;

    public UserInteractor(@NonNull LoginListener loginListener) {
        this.loginListener = loginListener;
        this.registerListener = null;
        this.recoveryListener = null;
        this.splashListener = null;
    }

    public UserInteractor(@NonNull RegisterListener registerListener) {
        this.registerListener = registerListener;
        this.loginListener = null;
        this.recoveryListener = null;
        this.splashListener = null;
    }

    public UserInteractor(@NonNull RecoveryListener recoveryListener) {
        this.recoveryListener = recoveryListener;
        this.loginListener = null;
        this.registerListener = null;
        this.splashListener = null;
    }

    public UserInteractor(@NonNull AuthenticationListener splashListener) {
        this.splashListener = splashListener;
        this.recoveryListener = null;
        this.loginListener = null;
        this.registerListener = null;
    }

    public void validateCredentials(final String user, final String password) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (validarPassword(password) & validarEmail(user)) {
                    if (UserRepository.getRepository().validarCredenciales(user, password)) {
                        if (loginListener != null) {
                            loginListener.onAuthenticationSucess();
                        }
                        if (splashListener != null) {
                            splashListener.onAuthenticationSucess();
                        }
                    } else {
                        if (loginListener != null) {
                            loginListener.onAuthenticationError();
                        }
                        if (splashListener != null) {
                            splashListener.onAuthenticationError();
                        }
                    }
                } else {
                    if (splashListener != null) {
                        splashListener.onAuthenticationError();
                    }
                }
            }
        }.execute();
    }

    public void validateRegister(final String nombre, final String email, final String password, final String passwordRepeat, final String birthDate) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Calendar calendar = Calendar.getInstance();
                if (validarFechaNac(birthDate) & validarPasswordRepeat(password, passwordRepeat) & validarPassword(password) & validarEmail(email) & validarNombre(nombre)) {
                    try {
                        calendar.setTime(MalagaSportApplication.DATE_FORMAT.parse(birthDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (UserRepository.getRepository().registrarUsuario(email, email, nombre, calendar, password)) {
                        registerListener.onRegisterSuccess();
                    } else {
                        registerListener.onRegisterFailed();
                    }
                }
            }
        }.execute();
    }

    public void validateChangePassword(final String email, final String password, final String passwordRepeat) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (validarPasswordRepeat(password, passwordRepeat) & validarPassword(password) & validarEmail(email)) {
                    if (UserRepository.getRepository().changePassword(email, password)) {
                        recoveryListener.onChangePasswordSuccess();
                    } else {
                        recoveryListener.onChangePasswordFailed();
                    }
                }
            }
        }.execute();
    }

    private boolean validarEmail(String email) {
        if (email.isEmpty()) {
            if (loginListener != null) {
                loginListener.onEmailEmptyError();
            }
            if (registerListener != null) {
                registerListener.onEmailEmptyError();
            }
            if (recoveryListener != null) {
                recoveryListener.onEmailEmptyError();
            }
            return false;
        }
        if (!MalagaSportApplication.VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
            if (loginListener != null) {
                loginListener.onInvalidEmailError();
            }
            if (registerListener != null) {
                registerListener.onInvalidEmailError();
            }
            if (recoveryListener != null) {
                recoveryListener.onInvalidEmailError();
            }
            return false;
        }
        if (loginListener != null) {
            loginListener.onValidEmail();
        }
        if (registerListener != null) {
            registerListener.onValidEmail();
        }
        if (recoveryListener != null) {
            recoveryListener.onValidEmail();
        }
        return true;
    }

    private boolean validarPassword(String password) {
        if (password.isEmpty()) {
            if (loginListener != null) {
                loginListener.onPasswordEmptyError();
            }
            if (registerListener != null) {
                registerListener.onPasswordEmptyError();
            }
            if (recoveryListener != null) {
                recoveryListener.onPasswordEmptyError();
            }
            return false;
        }
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > 20) {
            if (registerListener != null) {
                registerListener.onInvalidPasswordError();
            }
            if (recoveryListener != null) {
                recoveryListener.onInvalidPasswordError();
            }
            return false;
        }
        if (loginListener != null) {
            loginListener.onValidPassword();
        }
        if (registerListener != null) {
            registerListener.onValidPassword();
        }
        if (recoveryListener != null) {
            recoveryListener.onValidPassword();
        }
        return true;
    }

    private boolean validarFechaNac(String birthDate) {
        if (birthDate.isEmpty()) {
            registerListener.onBirthDateEmptyError();
            return false;
        }
        registerListener.onValidDate();
        return true;
    }

    private boolean validarPasswordRepeat(String password, String passwordRepeat) {
        if (!password.equals(passwordRepeat)) {
            if (registerListener != null) {
                registerListener.onPasswordRepeatError();
            }
            if (recoveryListener != null) {
                recoveryListener.onPasswordRepeatError();
            }
            return false;
        }
        if (registerListener != null) {
            registerListener.onValidPasswordRepeat();
        }
        if (recoveryListener != null) {
            recoveryListener.onValidPasswordRepeat();
        }
        return true;
    }

    private boolean validarNombre(String name) {
        if (name.isEmpty()) {
            registerListener.onNameEmptyError();
            return false;
        }
        registerListener.onValidName();
        return true;
    }

    private interface UserBaseListener {
        void onEmailEmptyError();

        void onInvalidEmailError();

        void onPasswordEmptyError();

        void onValidEmail();

        void onValidPassword();
    }

    public interface LoginListener extends UserBaseListener, AuthenticationListener { }

    private interface ValidatePasswordListener extends UserBaseListener {
        void onInvalidPasswordError();

        void onPasswordRepeatError();

        void onValidPasswordRepeat();
    }

    public interface RegisterListener extends ValidatePasswordListener {
        void onNameEmptyError();

        void onBirthDateEmptyError();

        void onValidName();

        void onValidDate();

        void onRegisterSuccess();

        void onRegisterFailed();
    }

    public interface RecoveryListener extends ValidatePasswordListener {
        void onChangePasswordSuccess();

        void onChangePasswordFailed();
    }

    public interface AuthenticationListener {
        void onAuthenticationSucess();

        void onAuthenticationError();
    }
}
