package com.bilalmoreno.malagasport.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.adapter.InstalacionAdapter;
import com.bilalmoreno.malagasport.pojo.Instalacion;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstalacionListActivity extends AppCompatActivity {

    @BindView(R.id.lvInstalaciones)
    ListView lvInstalaciones;

    InstalacionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instalacion_list);

        ButterKnife.bind(this);

        adapter = new InstalacionAdapter(getApplicationContext());
        lvInstalaciones.setAdapter(adapter);
    }
}
