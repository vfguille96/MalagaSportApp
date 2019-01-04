package com.bilalmoreno.malagasport.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.adapter.MaquinaAdapter;
import com.bilalmoreno.malagasport.pojo.Instalacion;
import com.bilalmoreno.malagasport.pojo.Maquina;
import com.bilalmoreno.malagasport.repository.InstalacionRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaquinaListActivity extends AppCompatActivity {

    Instalacion instalacion;
    MaquinaAdapter adapter;
    int idInstalacion;

    @BindView(R.id.lvMaquinas)
    ListView lvMaquinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina_list);

        ButterKnife.bind(this);

        idInstalacion = getIntent().getIntExtra("instalacion", -1);
        if (idInstalacion != -1) {
            instalacion = InstalacionRepository.getRepository().getInstalacion(idInstalacion);
            setTitle(instalacion.getNombre());
            adapter = new MaquinaAdapter(getApplicationContext(), idInstalacion, new Maquina.OrdenNivelAscendente());
            lvMaquinas.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.menu_maquina_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miOrdenarAscendente:
                adapter = new MaquinaAdapter(getApplicationContext(), idInstalacion, new Maquina.OrdenNivelAscendente());
                lvMaquinas.setAdapter(adapter);
                return true;
            case R.id.miOrdenDescendente:
                adapter = new MaquinaAdapter(getApplicationContext(), idInstalacion, new Maquina.OrdenNivelDescendente());
                lvMaquinas.setAdapter(adapter);
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
