package com.bilalmoreno.malagasport.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.ui.base.BaseActivity;
import com.bilalmoreno.malagasport.ui.dialog.DatePickerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {


    @BindView(R.id.tilNombre)
    TextInputLayout tilNombre;

    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;

    @BindView(R.id.tilPasswordRepeat)
    TextInputLayout tilPasswordRepeat;

    @BindView(R.id.tilFechaNac)
    TextInputLayout tilFechaNac;

    @BindView(R.id.tiedNombre)
    TextInputEditText tiedNombre;

    @BindView(R.id.tiedEmail)
    TextInputEditText tiedEmail;

    @BindView(R.id.tiedPassword)
    TextInputEditText tiedPassword;

    @BindView(R.id.tiedPasswordRepeat)
    TextInputEditText tiedPasswordRepeat;

    @BindView(R.id.tiedFechaNac)
    TextInputEditText tiedFechaNac;

    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tiedFechaNac)
    public void seleccionarFecha(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        ((DatePickerFragment) datePicker).setDateFormat(MalagaSportApplication.DATE_FORMAT);
        ((DatePickerFragment) datePicker).setEditText((EditText) view);
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    @OnClick(R.id.btRegistro)
    public void registrarUsuario(View view) {
        presenter = new RegisterPresenter(this);
        presenter.validateRegister(
                tiedNombre.getText().toString(),
                tiedEmail.getText().toString(),
                tiedPassword.getText().toString(),
                tiedPasswordRepeat.getText().toString(),
                tiedFechaNac.getText().toString()
        );
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    @Override
    public void setNameEmptyError() {
        tilNombre.setError(getString(R.string.msg_err_empty_name));
        requestFocus(tilNombre);
    }

    @Override
    public void setEmailEmptyError() {
        tilEmail.setError(getString(R.string.msg_err_empty_email));
        requestFocus(tiedEmail);
    }

    @Override
    public void setInvalidEmailError() {
        tilEmail.setError(getString(R.string.msg_err_invalid_email));
        requestFocus(tiedEmail);
    }

    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError(getString(R.string.msg_err_empty_password));
        requestFocus(tiedPassword);
    }

    @Override
    public void setPasswordInvalidError() {
        tilPassword.setError(getString(R.string.msg_err_invalid_password));
        requestFocus(tiedPassword);
    }

    @Override
    public void setPasswordRepeatError() {
        tilPasswordRepeat.setError(getString(R.string.msg_err_invalid_repeat_password));
        tilPasswordRepeat.setErrorEnabled(true);
        requestFocus(tiedPasswordRepeat);
    }

    @Override
    public void setBirthDateEmptyError() {
        tilFechaNac.setError(getString(R.string.msg_err_empty_fecha_nac));
    }

    @Override
    public void onRegisterSucess() {
        finish();
    }

    @Override
    public void hideNameError() {
        tilNombre.setErrorEnabled(false);
    }

    @Override
    public void hideEmailError() {
        tilEmail.setErrorEnabled(false);
    }

    @Override
    public void hidePasswordError() {
        tilPassword.setErrorEnabled(false);
    }

    @Override
    public void hidePasswordRepeatError() {
        tilPasswordRepeat.setErrorEnabled(false);
    }

    @Override
    public void hideDateError() {
        tilFechaNac.setErrorEnabled(false);
    }

    @Override
    public void setRegisterFailedError() {
        tilEmail.setError(getString(R.string.msg_err_register_failed));
    }
}
