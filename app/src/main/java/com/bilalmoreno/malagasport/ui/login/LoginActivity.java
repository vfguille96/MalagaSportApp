package com.bilalmoreno.malagasport.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.ui.MainNavigationActivity;
import com.bilalmoreno.malagasport.ui.base.BaseActivity;
import com.bilalmoreno.malagasport.ui.recovery.RecoveryActivity;
import com.bilalmoreno.malagasport.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.tilUsuario)
    TextInputLayout tilUsuario;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.tiedUsuario)
    TextInputEditText tiedUsuario;
    @BindView(R.id.tiedPassword)
    TextInputEditText tiedPassword;
    @BindView(R.id.cbRecordar)
    CheckBox cbRecordar;
    LoginContract.Presenter presenter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        SharedPreferences loginData = getSharedPreferences(MalagaSportApplication.LOGIN_DATA_TAG, Context.MODE_PRIVATE);

        String user = loginData.getString(MalagaSportApplication.USER_TAG, "");
        String password = loginData.getString(MalagaSportApplication.PASSWORD_TAG, "");

        presenter = new LoginPresenter(this);

        if (!user.isEmpty() && !password.isEmpty()) {
            presenter.validateCredentials(user, password);
        } else {
            tiedUsuario.setText(user);
            tiedPassword.setText(password);
        }
    }

    @OnClick(R.id.btIniciar)
    public void iniciarSesion(View view) {

        presenter.validateCredentials(tiedUsuario.getText().toString(), tiedPassword.getText().toString());
    }

    @OnClick(R.id.btRecuperar)
    public void recuperar(View view) {
        intent = new Intent(LoginActivity.this, RecoveryActivity.class);
        startActivity(intent);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @OnClick(R.id.btRegistro)
    public void registrarse(View view) {
        intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setEmailEmptyError() {
        tilUsuario.setError(getString(R.string.msg_err_empty_email));
        requestFocus(tiedUsuario);
    }

    @Override
    public void setInvalidEmailError() {
        tilUsuario.setError(getString(R.string.msg_err_invalid_email));
        requestFocus(tiedUsuario);
    }

    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError(getString(R.string.msg_err_empty_password));
        requestFocus(tiedPassword);
    }

    @Override
    public void setAuthenticationFailedError() {
        tilUsuario.setError(getString(R.string.msg_err_invalid_credentials));
        requestFocus(tiedUsuario);
    }

    @Override
    public void onAuthenticationSucess() {
        if (cbRecordar.isChecked()) {
            SharedPreferences loginData = getSharedPreferences(MalagaSportApplication.LOGIN_DATA_TAG, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = loginData.edit();
            editor.putString(MalagaSportApplication.USER_TAG, tiedUsuario.getText().toString());
            editor.putString(MalagaSportApplication.PASSWORD_TAG, tiedPassword.getText().toString());
            editor.apply();
        }
        intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void hideEmailError() {
        tilUsuario.setErrorEnabled(false);
    }

    @Override
    public void hidePasswordError() {
        tilPassword.setErrorEnabled(false);
    }

}
