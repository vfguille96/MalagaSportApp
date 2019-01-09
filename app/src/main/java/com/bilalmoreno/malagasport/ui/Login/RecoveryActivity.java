package com.bilalmoreno.malagasport.ui.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecoveryActivity extends BaseActivity implements RecoveryContract.View {

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

    private RecoveryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btCambiar)
    public void recuperar(View view) {
        presenter = new RecoveryPresenter(this);
        presenter.validateChangePassword(
                tiedEmail.getText().toString(),
                tiedPassword.getText().toString(),
                tiedPasswordRepeat.getText().toString()
        );
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
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
        requestFocus(tiedPasswordRepeat);
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
    public void onChangePasswordSuccess() {
        finish();
    }

    @Override
    public void onChangePasswordFailed() {
        tilEmail.setError(getString(R.string.msg_err_account_not_exists));
    }
}
