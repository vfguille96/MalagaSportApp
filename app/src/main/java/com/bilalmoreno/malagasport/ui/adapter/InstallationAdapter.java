package com.bilalmoreno.malagasport.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InstallationAdapter extends RecyclerView.Adapter<InstallationAdapter.InstallationHolder> {

    private final Context context;
    private InstallationListListener listener;
    private ArrayList<Installation> instalaciones;

    public InstallationAdapter(Context context, InstallationListListener listener) {
        this.context = context;
        this.listener = listener;
        this.instalaciones = new ArrayList<>();
    }

    public void clear() {
        instalaciones.clear();
    }

    public void addAll(ArrayList<Installation> installations) {
        this.instalaciones.addAll(installations);
    }

    @NonNull
    @Override
    public InstallationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new InstallationHolder(layoutInflater.inflate(R.layout.item_instalacion, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstallationHolder installationHolder, int i) {
        Installation installation = instalaciones.get(i);
        installationHolder.itemView.setOnClickListener(listener);
        installationHolder.tvNombre.setText(installation.getNombre());
        installationHolder.tvDireccion.setText(installation.getDireccion());
        if (!installation.getAccesoMovReducida()) {
            installationHolder.ivMovRed.setVisibility(View.GONE);
        }
        if (!installation.getIluminacion()) {
            installationHolder.ivIluminacion.setVisibility(View.GONE);
        }
        if (UserRepository.getRepository().getUsuario().getFavoritos().contains(installation.getId())) {
            installationHolder.ivFav.setImageResource(R.drawable.ic_fav_32dp);
        } else {
            installationHolder.ivFav.setImageResource(R.drawable.ic_no_fav_32dp);
        }
    }

    @Override
    public int getItemCount() {
        return instalaciones.size();
    }

    public Installation getItem(int position) {
        return instalaciones.get(position);
    }

    public void sort(Comparator<Installation> comparator) {
        Collections.sort(instalaciones, comparator);
    }

    public interface InstallationListListener extends View.OnClickListener {
    }

    public class InstallationHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDireccion;
        ImageView ivMovRed;
        ImageView ivIluminacion;
        ImageView ivFav;

        public InstallationHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            ivMovRed = itemView.findViewById(R.id.ivMovRed);
            ivIluminacion = itemView.findViewById(R.id.ivIluminacion);
            ivFav = itemView.findViewById(R.id.ivFav);
        }
    }
}
