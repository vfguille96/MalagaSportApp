package com.bilalmoreno.malagasport.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bilalmoreno.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.repository.UsuarioRepository;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_DATA_TAG = "loginData";
    private static final String USER_TAG = "userEmail";
    private static final String PASSWORD_TAG = "password";


    private Intent intent;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        SharedPreferences loginData = getSharedPreferences(LOGIN_DATA_TAG, Context.MODE_PRIVATE);
        tiedUsuario.setText(loginData.getString(USER_TAG, ""));
        tiedPassword.setText(loginData.getString(PASSWORD_TAG, ""));
    }

    @OnClick(R.id.btIniciar)
    public void iniciarSesion(View view) {
        if (validar()) {
            if (cbRecordar.isChecked()) {
                SharedPreferences loginData = getSharedPreferences(LOGIN_DATA_TAG, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = loginData.edit();
                editor.putString(USER_TAG, tiedUsuario.getText().toString());
                editor.putString(PASSWORD_TAG, tiedPassword.getText().toString());
                editor.apply();
            }
            intent = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.btRecuperar)
    public void recuperar(View view) {
        intent = new Intent(LoginActivity.this, RecuperarActivity.class);
        startActivity(intent);
    }

    private boolean validar() {
        return validarEmail() & validarPassword() && validarCredenciales();
    }

    private boolean validarEmail() {
        if (tiedUsuario.getText().toString().isEmpty()) {
            tilUsuario.setError(getString(R.string.msg_err_empty_email));
            requestFocus(tiedUsuario);
            return false;
        }
        if (!MalagaSportApplication.VALID_EMAIL_ADDRESS_REGEX.matcher(tiedUsuario.getText().toString()).find()) {
            tilUsuario.setError(getString(R.string.msg_err_invalid_email));
            return false;
        }
        tilUsuario.setErrorEnabled(false);
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private boolean validarPassword() {
        if (tiedPassword.getText().toString().isEmpty()) {
            tilPassword.setError(getString(R.string.msg_err_empty_password));
            requestFocus(tiedPassword);
            return false;
        }
        tilPassword.setErrorEnabled(false);
        return true;
    }

    private boolean validarCredenciales() {
        if (!UsuarioRepository.getRepository().validarCredenciales(tiedUsuario.getText().toString(), tiedPassword.getText().toString())) {
            tilUsuario.setError(getString(R.string.msg_err_invalid_credentials));
            requestFocus(tiedUsuario);
            return false;
        }
        tilUsuario.setErrorEnabled(false);
        return true;
    }

    @OnClick(R.id.btRegistro)
    public void registrarse(View view) {
        intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
