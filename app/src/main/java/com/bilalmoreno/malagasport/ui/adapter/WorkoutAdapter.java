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
import com.bilalmoreno.malagasport.data.db.repository.UsuarioRepository;

import java.util.ArrayList;


public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    private final Context context;
    private WorkoutListListener listener;
    private ArrayList<Installation> instalaciones;

    public WorkoutAdapter(Context context, WorkoutListListener listener) {
        this.context = context;
        this.listener = listener;
        instalaciones = new ArrayList<>();
    }

    public void clear() {
        instalaciones.clear();
    }

    public void addAll(ArrayList<Installation> installations) {
        this.instalaciones.addAll(installations);
    }

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new WorkoutHolder(layoutInflater.inflate(R.layout.item_workout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder workoutHolder, int i) {
        Installation installation = instalaciones.get(i);
        workoutHolder.itemView.setOnClickListener(listener);
        workoutHolder.tvNombre.setText(installation.getNombre());
        workoutHolder.tvDireccion.setText(installation.getDireccion());
        workoutHolder.tvNumeMaquinas.setText(String.valueOf(installation.getMaquinas().size()));
        workoutHolder.tvNiveles.setText(installation.getNiveles());
        workoutHolder.ivMovReducida.setImageResource(R.drawable.ic_mov_red);
        if (!installation.getAccesoMovReducida()) {
            workoutHolder.ivMovReducida.setVisibility(View.GONE);
        }
        if (UsuarioRepository.getRepository().getUsuario().getFavoritos().contains(installation.getId())) {
            workoutHolder.ivFav.setImageResource(R.drawable.ic_fav_32dp);
        } else {
            workoutHolder.ivFav.setImageResource(R.drawable.ic_no_fav_32dp);
        }
    }

    @Override
    public int getItemCount() {
        return instalaciones.size();
    }

    public Installation getItem(int position) {
        return instalaciones.get(position);
    }

    public interface WorkoutListListener extends View.OnClickListener {
    }

    public class WorkoutHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvDireccion;
        TextView tvNiveles;
        TextView tvNumeMaquinas;
        ImageView ivFav;
        ImageView ivMovReducida;

        public WorkoutHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvNiveles = itemView.findViewById(R.id.tvNiveles);
            tvNumeMaquinas = itemView.findViewById(R.id.tvNumMaquinas);
            ivFav = itemView.findViewById(R.id.ivFav);
            ivMovReducida = itemView.findViewById(R.id.ivMovRed);
        }
    }
}
