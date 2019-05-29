package com.bilalmoreno.malagasport.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Workout;
import com.bilalmoreno.malagasport.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    private final Context context;
    private WorkoutListListener listener;
    private ArrayList<Workout> workouts;

    public WorkoutAdapter(Context context, WorkoutListListener listener) {
        this.context = context;
        this.listener = listener;
        workouts = new ArrayList<>();
    }

    public void clear() {
        workouts.clear();
    }

    public void addAll(ArrayList<Workout> workouts) {
        this.workouts.addAll(workouts);
    }

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new WorkoutHolder(layoutInflater.inflate(R.layout.item_workout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder workoutHolder, int i) {
        Workout workout = workouts.get(i);
        workoutHolder.itemView.setOnClickListener(listener);
        workoutHolder.tvNombre.setText(workout.getNombre());
        workoutHolder.tvDireccion.setText(workout.getDireccion());
//        workoutHolder.tvNumeMaquinas.setText(String.valueOf(workout.getMaquinas().size()));
//        workoutHolder.tvNiveles.setText(workout.getNiveles());
//        workoutHolder.ivMovReducida.setImageResource(R.drawable.ic_mov_red);
//        if (!workout.getAccesoMovReducida()) {
//            workoutHolder.ivMovReducida.setVisibility(View.GONE);
//        }
        if (UserRepository.getInstance().getUser(MalagaSportApplication.getUserId()).getFavoritos().contains(workout.getId())) {
            workoutHolder.ivFav.setImageResource(R.drawable.ic_fav_32dp);
        } else {
            workoutHolder.ivFav.setImageResource(R.drawable.ic_no_fav_32dp);
        }
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public Workout getItem(int position) {
        return workouts.get(position);
    }

    public void sort(Comparator<Workout> comparator) {
        Collections.sort(workouts, comparator);
        notifyDataSetChanged();
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
