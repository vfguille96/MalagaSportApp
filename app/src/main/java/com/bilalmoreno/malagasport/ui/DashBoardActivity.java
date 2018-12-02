package com.bilalmoreno.malagasport.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.pojo.Usuario;
import com.bilalmoreno.malagasport.repository.UsuarioRepository;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ButterKnife.bind(this);
        Usuario usuario = new Usuario("bilalmoreno92@gmail.com", "bilalmoreno92@gmail.com", "BilalMoreno92", Calendar.getInstance());
        usuario.addFavorito(1);
        UsuarioRepository.getRepository().setUsuario(usuario);

    }

    @OnClick({R.id.btInstalaciones, R.id.btWorkout, R.id.btEventos, R.id.btFavoritos, R.id.btConfiguracion, R.id.btAbout})
    public void openActivity(View view){
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btInstalaciones:
                intent = new Intent(DashBoardActivity.this, InstalacionListActivity.class);
                break;
            case R.id.btWorkout:
                intent = new Intent(DashBoardActivity.this, WorkoutListActivity.class);
                break;
            case R.id.btAbout:
                intent = new Intent(DashBoardActivity.this, AboutActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
