package com.bilalmoreno.malagasport.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.bilalmoreno.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.pojo.Usuario;
import com.bilalmoreno.malagasport.repository.UsuarioRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class RegisterActivity extends AppCompatActivity {

    private static final int MIN_PASSWORD_LENGTH = 6;

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
        Calendar calendar = Calendar.getInstance();
        if (validar()) {
            try {
                calendar.setTime(MalagaSportApplication.DATE_FORMAT.parse(tiedFechaNac.getText().toString()));
            } catch (ParseException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (UsuarioRepository.getRepository().registrarUsuario(tiedEmail.getText().toString(), tiedEmail.getText().toString(), tiedNombre.getText().toString(), calendar, tiedPassword.getText().toString())) {
                startActivity(new Intent(RegisterActivity.this, DashBoardActivity.class));
                finish();
            }
        }
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private boolean validar() {
        return validarNombre() & validarEmail() & validarPassword() & validarPasswordRepeat() & validarFechaNac();
    }

    private boolean validarFechaNac() {
        if (tiedFechaNac.getText().toString().isEmpty()) {
            tilFechaNac.setError(getString(R.string.msg_err_empty_fecha_nac));
            return false;
        }
        tilNombre.setErrorEnabled(false);
        return true;
    }

    private boolean validarPassword() {
        if (tiedPassword.getText().length() < MIN_PASSWORD_LENGTH || tiedPassword.getText().length() > 20) {
            tilPassword.setError(getString(R.string.msg_err_invalid_password));
            requestFocus(tiedPassword);
            return false;
        }
        tilPassword.setErrorEnabled(false);
        return true;
    }

    private boolean validarPasswordRepeat() {
        if (!tiedPassword.getText().toString().equals(tiedPasswordRepeat.getText().toString())) {
            tilPasswordRepeat.setError(getString(R.string.msg_err_invalid_repeat_password));
            tilPasswordRepeat.setErrorEnabled(true);
            requestFocus(tiedPasswordRepeat);
            return false;
        }
        tilPasswordRepeat.setErrorEnabled(false);
        return true;
    }

    private boolean validarEmail() {
        if (tiedEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getString(R.string.msg_err_empty_email));
            return false;
        }
        if (!MalagaSportApplication.VALID_EMAIL_ADDRESS_REGEX.matcher(tiedEmail.getText().toString()).find()) {
            tilEmail.setError(getString(R.string.msg_err_invalid_email));
            return false;
        }
        tilNombre.setErrorEnabled(false);
        return true;
    }

    private boolean validarNombre() {
        if (tiedNombre.getText().toString().isEmpty()) {
           tilNombre.setError(getString(R.string.msg_err_empty_name));
           return false;
        }
        tilNombre.setErrorEnabled(false);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Calendar calendar = Calendar.getInstance();
        private SimpleDateFormat dateFormat;
        private EditText editText;

        public void setDateFormat(SimpleDateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int year;
            int month;
            int day;
            if (!editText.getText().toString().isEmpty()) {

                try {
                    calendar.setTime(dateFormat.parse(editText.getText().toString()));
                } catch (ParseException e) {
                    Toast.makeText(getContext(), "Fecha no válida", Toast.LENGTH_SHORT).show();
                }
            }

            // Use the current date as the default date in the picker
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            calendar.set(year, month, day);
            dateFormat.setCalendar(calendar);
            editText.setText(dateFormat.format(calendar.getTime()));
        }

        public void setEditText(EditText editText) {
            this.editText = editText;
        }

        public Calendar getCalendar() {
            return calendar;
        }
    }
}
