package com.bilalmoreno.malagasport.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.pojo.Instalacion;
import com.bilalmoreno.malagasport.pojo.Maquina;
import com.bilalmoreno.malagasport.repository.InstalacionRepository;

import butterknife.ButterKnife;

public class MaquinaListActivity extends AppCompatActivity {

    Instalacion instalacion;
    int idInstalacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_list);

        ButterKnife.bind(this);

        idInstalacion = getIntent().getIntExtra("instalacion", -1);
        if (idInstalacion != -1) {
            instalacion = InstalacionRepository.getRepository().getInstalacion(idInstalacion);
            Toast.makeText(this, String.valueOf(instalacion.getId()), Toast.LENGTH_SHORT).show();
        }
    }
}
