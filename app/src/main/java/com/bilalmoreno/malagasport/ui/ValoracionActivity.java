package com.bilalmoreno.malagasport.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.pojo.Instalacion;
import com.bilalmoreno.malagasport.pojo.Usuario;
import com.bilalmoreno.malagasport.pojo.Valoracion;
import com.bilalmoreno.malagasport.repository.UsuarioRepository;
import com.bilalmoreno.malagasport.repository.ValoracionRepository;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ValoracionActivity extends AppCompatActivity {

    private Valoracion valoracion;
    int estrellas;
    int idInstalacion;
    int actionCode;

    @BindView(R.id.ivStar1)
    ImageView ivStar1;

    @BindView(R.id.ivStar2)
    ImageView ivStar2;

    @BindView(R.id.ivStar3)
    ImageView ivStar3;

    @BindView(R.id.ivStar4)
    ImageView ivStar4;

    @BindView(R.id.ivStar5)
    ImageView ivStar5;

    @BindView(R.id.tilComentario)
    TextInputLayout tilComentario;

    @BindView(R.id.tiedComentario)
    TextInputEditText tiedComentario;

    private String idUsuario;
    private Intent intent;

    @OnClick(R.id.btGuardar)
    public void guardar(View view){
        if (validar()) {
            Intent valoracionIntent = new Intent();
            if (actionCode == InstalacionActivity.VALORACION_ADD) {
                valoracion = new Valoracion(idInstalacion, idUsuario, Calendar.getInstance().getTime(), estrellas, tiedComentario.getText().toString());
                valoracionIntent.putExtra(Valoracion.TAG, valoracion);
            } else {
                Valoracion valoracionEdit = new Valoracion(idInstalacion, idUsuario, valoracion.getFecha(), estrellas, tiedComentario.getText().toString());
                valoracionIntent.putExtra(Valoracion.TAG, valoracionEdit);

            }
            setResult(AppCompatActivity.RESULT_OK, valoracionIntent);
            finish();
        }
    }

    private boolean validar() {
        return validarEstrellas() && validarComentario();
    }

    private boolean validarComentario() {
        if (tiedComentario.getText().toString().isEmpty()) {
            tilComentario.setError(getString(R.string.msg_err_empty_comment));
            requestFocus(tiedComentario);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private boolean validarEstrellas() {
        if (estrellas > 0 && estrellas <= 5) {
            return true;
        }
        Toast.makeText(this, R.string.msg_err_valoracion, Toast.LENGTH_SHORT).show();
        return false;
    }

    @OnClick({R.id.ivStar1, R.id.ivStar2, R.id.ivStar3, R.id.ivStar4, R.id.ivStar5})
    public void setEstrellas(View view) {
        switch (view.getId()) {
            case R.id.ivStar1:
                estrellas = 1;
                ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                ivStar2.setImageResource(R.drawable.ic_no_fav_32dp);
                ivStar3.setImageResource(R.drawable.ic_no_fav_32dp);
                ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
                ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                break;
            case R.id.ivStar2:
                estrellas = 2;
                ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                ivStar3.setImageResource(R.drawable.ic_no_fav_32dp);
                ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
                ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                break;
            case R.id.ivStar3:
                estrellas = 3;
                ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
                ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                break;
            case R.id.ivStar4:
                estrellas = 4;
                ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                ivStar4.setImageResource(R.drawable.ic_fav_32dp);
                ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
                break;
            case R.id.ivStar5:
                estrellas = 5;
                ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                ivStar4.setImageResource(R.drawable.ic_fav_32dp);
                ivStar5.setImageResource(R.drawable.ic_fav_32dp);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion);

        ButterKnife.bind(this);

        intent = getIntent();
        if (intent != null) {
            actionCode = intent.getIntExtra(InstalacionActivity.ACTION, -1);
            idInstalacion = intent.getIntExtra(Instalacion.TAG, -1);
            idUsuario = intent.getStringExtra(Usuario.TAG);
            if (idUsuario != null && actionCode != -1 && idInstalacion != -1) {
                if (actionCode == InstalacionActivity.VALORACION_EDIT) {
                    setTitle(R.string.editar_valoracion);
                    valoracion = ValoracionRepository.getRepository().getValoracion(idUsuario, idInstalacion);
                    tiedComentario.setText(valoracion.getComentario());
                    estrellas = valoracion.getEstrellas();
                    switch (estrellas) {
                        case 5:
                            ivStar5.setImageResource(R.drawable.ic_fav_32dp);
                        case 4:
                            ivStar4.setImageResource(R.drawable.ic_fav_32dp);
                        case 3:
                            ivStar3.setImageResource(R.drawable.ic_fav_32dp);
                        case 2:
                            ivStar2.setImageResource(R.drawable.ic_fav_32dp);
                        case 1:
                            ivStar1.setImageResource(R.drawable.ic_fav_32dp);
                    }
                } else {
                    setTitle(R.string.nueva_valoracion);
                    estrellas = 0;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("BACK", "OnBackPressed");
        setResult(AppCompatActivity.RESULT_CANCELED);
        finish();
    }
}
