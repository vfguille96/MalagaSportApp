package com.bilalmoreno.malagasport.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Valoration;
import com.bilalmoreno.malagasport.data.db.repository.UserRepository;

public class ValoracionAdapter extends ArrayAdapter {

    public ValoracionAdapter(@NonNull Context context) {
        super(context, R.layout.item_valoracion);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Valoration valoration;
        ValoracionHolder valoracionHolder;

        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_valoracion, null);
            valoracionHolder = new ValoracionHolder();
            valoracionHolder.tvUsuario = view.findViewById(R.id.tvUsuario);
            valoracionHolder.tvDate = view.findViewById(R.id.tvDate);
            valoracionHolder.tvComentario = view.findViewById(R.id.tvComentario);
            valoracionHolder.ivStar1 = view.findViewById(R.id.ivStar1);
            valoracionHolder.ivStar2 = view.findViewById(R.id.ivStar2);
            valoracionHolder.ivStar3 = view.findViewById(R.id.ivStar3);
            valoracionHolder.ivStar4 = view.findViewById(R.id.ivStar4);
            valoracionHolder.ivStar5 = view.findViewById(R.id.ivStar5);
            view.setTag(valoracionHolder);
        } else {
            valoracionHolder = (ValoracionHolder) view.getTag();
        }

        valoration = getItem(position);
        valoracionHolder.tvUsuario.setText(UserRepository.getRepository().getUsuario(valoration.getIdUsuario()).getNombre());
        valoracionHolder.tvDate.setText(MalagaSportApplication.DATE_FORMAT.format(valoration.getFechaEdicion().getTime()));
        valoracionHolder.tvComentario.setText(valoration.getComentario());
        valoracionHolder.ivStar1.setImageResource(R.drawable.ic_no_fav_32dp);
        valoracionHolder.ivStar2.setImageResource(R.drawable.ic_no_fav_32dp);
        valoracionHolder.ivStar3.setImageResource(R.drawable.ic_no_fav_32dp);
        valoracionHolder.ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
        valoracionHolder.ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
        switch (valoration.getEstrellas()) {
            case 5:
                valoracionHolder.ivStar5.setImageResource(R.drawable.ic_fav_32dp);
            case 4:
                valoracionHolder.ivStar4.setImageResource(R.drawable.ic_fav_32dp);
            case 3:
                valoracionHolder.ivStar3.setImageResource(R.drawable.ic_fav_32dp);
            case 2:
                valoracionHolder.ivStar2.setImageResource(R.drawable.ic_fav_32dp);
            case 1:
                valoracionHolder.ivStar1.setImageResource(R.drawable.ic_fav_32dp);
        }
        return view;
    }

    @Nullable
    @Override
    public Valoration getItem(int position) {
        return (Valoration) super.getItem(position);
    }

    private class ValoracionHolder {
        TextView tvUsuario;
        TextView tvDate;
        TextView tvComentario;
        ImageView ivStar1;
        ImageView ivStar2;
        ImageView ivStar3;
        ImageView ivStar4;
        ImageView ivStar5;
    }
}
