package com.bilalmoreno.malagasport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.pojo.Maquina;
import com.bilalmoreno.malagasport.repository.InstalacionRepository;
import com.bilalmoreno.malagasport.repository.MaquinaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MaquinaAdapter extends ArrayAdapter {

    ArrayList<Maquina> maquinas;

    public MaquinaAdapter(@NonNull Context context, int idInstalacion, Comparator<Maquina> orden) {
        super(context, R.layout.item_maquina);
        maquinas = MaquinaRepository.getRepository().getMaquinas(InstalacionRepository.getRepository().getInstalacion(idInstalacion).getMaquinas());
        Collections.sort(maquinas, orden);
        addAll(maquinas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Maquina maquina;
        MaquinaHolder maquinaHolder;

        if (view == null) {
            view = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_maquina, null);
            maquinaHolder = new MaquinaHolder();
            maquinaHolder.tvNombre = view.findViewById(R.id.tvNombre);
            maquinaHolder.tvNivelMaquina = view.findViewById(R.id.tvNivelMaquina);
            view.setTag(maquinaHolder);
        } else {
            maquinaHolder = (MaquinaHolder) view.getTag();
        }

        maquina = (Maquina) getItem(position);
        maquinaHolder.tvNombre.setText(maquina.getNombre());
        maquinaHolder.tvNivelMaquina.setText(String.valueOf(maquina.getNivel()));

        return view;
    }

    private class MaquinaHolder {
        TextView tvNombre;
        TextView tvNivelMaquina;
    }
}
