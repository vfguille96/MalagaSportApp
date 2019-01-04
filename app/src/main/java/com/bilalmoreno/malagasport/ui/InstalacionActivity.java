package com.bilalmoreno.malagasport.ui;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.adapter.ValoracionAdapter;
import com.bilalmoreno.malagasport.pojo.Instalacion;
import com.bilalmoreno.malagasport.pojo.Usuario;
import com.bilalmoreno.malagasport.pojo.Valoracion;
import com.bilalmoreno.malagasport.repository.InstalacionRepository;
import com.bilalmoreno.malagasport.repository.UsuarioRepository;
import com.bilalmoreno.malagasport.repository.ValoracionRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstalacionActivity extends AppCompatActivity {

    public static final int VALORACION_ADD = 0;
    public static final int VALORACION_EDIT = 1;
    public static final String ACTION = "action";
    Instalacion instalacion;
    ValoracionAdapter adapter;

    @BindView(R.id.tvDireccion)
    TextView tvDireccion;

    @BindView(R.id.ivMovRed)
    ImageView ivMovRed;

    @BindView(R.id.ivIluminacion)
    ImageView ivIluminacion;

    @BindView(R.id.lvValoraciones)
    ListView lvValoraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instalacion);

        ButterKnife.bind(this);

        final Intent intent = getIntent();
        if (intent != null) {
            int idInstalacion = intent.getIntExtra(Instalacion.TAG, -1);
            if (idInstalacion != -1) {
                instalacion = InstalacionRepository.getRepository().getInstalacion(idInstalacion);
                setTitle(instalacion.getNombre());
                tvDireccion.setText(instalacion.getDireccion());
                if (!instalacion.getIluminacion()) {
                    ivIluminacion.setVisibility(View.GONE);
                }
                if (!instalacion.getAccesoMovReducida()) {
                    ivMovRed.setVisibility(View.GONE);
                }
                adapter = new ValoracionAdapter(getApplicationContext(), idInstalacion);
                lvValoraciones.setAdapter(adapter);
                lvValoraciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Valoracion valoracion = adapter.getItem(position);
                        if (valoracion.getIdUsuario().equals(UsuarioRepository.getRepository().getUsuario().getId())) {
                            Intent valoracionIntent = new Intent(InstalacionActivity.this, ValoracionActivity.class);
                            valoracionIntent.putExtra(ACTION, VALORACION_EDIT);
                            valoracionIntent.putExtra(Instalacion.TAG, valoracion.getIdInstlacion());
                            valoracionIntent.putExtra(Usuario.TAG, valoracion.getIdUsuario());
                            startActivityForResult(valoracionIntent, VALORACION_EDIT);
                        }
                    }
                });
            }
        }
    }

    @OnClick(R.id.btAdd)
    public void addValoracion(View view){
        Intent valoracionIntent = new Intent(InstalacionActivity.this, ValoracionActivity.class);
        valoracionIntent.putExtra(ACTION, VALORACION_ADD);
        valoracionIntent.putExtra(Instalacion.TAG, instalacion.getId());
        valoracionIntent.putExtra(Usuario.TAG, UsuarioRepository.getRepository().getUsuario().getId());
        startActivityForResult(valoracionIntent, VALORACION_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            Valoracion valoracion = (Valoracion) data.getSerializableExtra(Valoracion.TAG);
            if (requestCode == VALORACION_ADD) {
                ValoracionRepository.getRepository().add(valoracion);
            } else {
                ValoracionRepository.getRepository().edit(valoracion);
            }
//            adapter = new ValoracionAdapter(getApplicationContext(), valoracion.getIdInstlacion());
//            lvValoraciones.setAdapter(adapter);
            adapter.reloadData(instalacion.getId());
        }
    }
}
