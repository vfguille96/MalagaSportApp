package com.bilalmoreno.malagasport.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Usuario;
import com.bilalmoreno.malagasport.data.db.repository.UsuarioRepository;
import com.bilalmoreno.malagasport.ui.installation.InstallationActivity;
import com.bilalmoreno.malagasport.ui.workout.WorkoutListFragment;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashBoardActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ButterKnife.bind(this);
        Usuario usuario = new Usuario("bilalmoreno92@gmail.com", "bilalmoreno92@gmail.com", "BilalMoreno92", Calendar.getInstance());
        usuario.addFavorito(1);
        UsuarioRepository.getRepository().setUsuario(usuario);

    }

    @OnClick({R.id.btInstalaciones, R.id.btWorkout, R.id.btEventos, R.id.btFavoritos})
    public void openActivity(View view) {
        switch (view.getId()) {
            case R.id.btInstalaciones:
                intent = new Intent(DashBoardActivity.this, InstallationActivity.class);
                break;
            case R.id.btWorkout:
                intent = new Intent(DashBoardActivity.this, WorkoutListFragment.class);
                break;
            case R.id.btFavoritos:
                intent = null;
                break;
            case R.id.btEventos:
                intent = new Intent(DashBoardActivity.this, AboutActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.menu_dash_board, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miConfiguracion:
                intent = new Intent(DashBoardActivity.this, SettingsFragment.class);
                startActivity(intent);
                return true;
            case R.id.miAbout:
                intent = new Intent(DashBoardActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
