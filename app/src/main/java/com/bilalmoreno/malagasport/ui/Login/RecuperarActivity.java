package com.bilalmoreno.malagasport.ui.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.repository.UsuarioRepository;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecuperarActivity extends AppCompatActivity {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 20;

    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;

    @BindView(R.id.tilPasswordRepeat)
    TextInputLayout tilPasswordRepeat;

    @BindView(R.id.tiedEmail)
    TextInputEditText tiedEmail;

    @BindView(R.id.tiedPassword)
    TextInputEditText tiedPassword;

    @BindView(R.id.tiedPasswordRepeat)
    TextInputEditText tiedPasswordRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btCambiar)
    public void recuperar(View view) {
        if (validar()) {
            UsuarioRepository.getRepository().cambiarPassword(tiedEmail.getText().toString(), tiedPassword.getText().toString());
            Toast.makeText(this, getString(R.string.msg_password_changed), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean validar() {
        return validarEmail() & validarPassword();
    }

    private boolean validarEmail() {
        if (tiedEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getString(R.string.msg_err_empty_email));
            requestFocus(tiedEmail);
            return false;
        }
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(tiedEmail.getText().toString()).find()) {
            tilEmail.setError(getString(R.string.msg_err_invalid_email));
            return false;
        }
        tilEmail.setErrorEnabled(false);
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private boolean validarPassword() {
        if (tiedPassword.getText().length() < MIN_PASSWORD_LENGTH || tiedPassword.getText().length() > MAX_PASSWORD_LENGTH) {
            tilPassword.setError(getString(R.string.msg_err_invalid_password));
            return false;
        }
        if (!tiedPassword.getText().toString().equals(tiedPasswordRepeat.getText().toString())) {
            tilPasswordRepeat.setError(getString(R.string.msg_err_invalid_repeat_password));
            tilPassword.setErrorEnabled(true);
            requestFocus(tiedPasswordRepeat);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
