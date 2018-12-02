package com.bilalmoreno.malagasport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.pojo.Instalacion;
import com.bilalmoreno.malagasport.repository.InstalacionRepository;
import com.bilalmoreno.malagasport.repository.UsuarioRepository;

public class InstalacionAdapter extends ArrayAdapter {
    public InstalacionAdapter(@NonNull Context context) {
        super(context, R.layout.item_instalacion, InstalacionRepository.getRepository().getInstalaciones());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Instalacion instalacion;
        InstalacionHolder instalacionHolder;

        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_instalacion, null);
            instalacionHolder = new InstalacionHolder();
            instalacionHolder.tvNombre = view.findViewById(R.id.tvNombre);
            instalacionHolder.tvDireccion = view.findViewById(R.id.tvDireccion);
            instalacionHolder.ivMovRed = view.findViewById(R.id.ivMovRed);
            instalacionHolder.ivIluminacion = view.findViewById(R.id.ivIluminacion);
            instalacionHolder.ivFav = view.findViewById(R.id.ivFav);
            view.setTag(instalacionHolder);

        } else {
            instalacionHolder = (InstalacionHolder) view.getTag();
        }

        instalacion = (Instalacion) getItem(position);
        instalacionHolder.tvNombre.setText(instalacion.getNombre());
        instalacionHolder.tvDireccion.setText(instalacion.getDireccion());
        instalacionHolder.ivIluminacion.setImageResource(R.drawable.ic_iluminacion);
        instalacionHolder.ivMovRed.setImageResource(R.drawable.ic_mov_red);
        if (!instalacion.getAccesoMovReducida()) {
            instalacionHolder.ivMovRed.setVisibility(View.GONE);
        }
        if (!instalacion.getIluminacion()) {
            instalacionHolder.ivIluminacion.setVisibility(View.GONE);
        }
        if (UsuarioRepository.getRepository().getUsuario().getFavoritos().contains(instalacion.getId())) {
            instalacionHolder.ivFav.setImageResource(R.drawable.ic_fav_32dp);
        } else {
            instalacionHolder.ivFav.setImageResource(R.drawable.ic_no_fav_32dp);
        }
        return view;
    }

    private class InstalacionHolder {
        TextView tvNombre;
        TextView tvDireccion;
        ImageView ivMovRed;
        ImageView ivIluminacion;
        ImageView ivFav;
    }
}
