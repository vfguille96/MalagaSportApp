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
import com.bilalmoreno.malagasport.data.db.model.Rate;
import com.bilalmoreno.malagasport.data.repository.UserRepository;

public class RateAdapter extends ArrayAdapter {

    public RateAdapter(@NonNull Context context) {
        super(context, R.layout.item_rate);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Rate rate;
        RateHolder rateHolder;

        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_rate, null);
            rateHolder = new RateHolder();
            rateHolder.tvUsuario = view.findViewById(R.id.tvUsuario);
            rateHolder.tvDate = view.findViewById(R.id.tvDate);
            rateHolder.tvComentario = view.findViewById(R.id.tvComentario);
            rateHolder.ivStar1 = view.findViewById(R.id.ivStar1);
            rateHolder.ivStar2 = view.findViewById(R.id.ivStar2);
            rateHolder.ivStar3 = view.findViewById(R.id.ivStar3);
            rateHolder.ivStar4 = view.findViewById(R.id.ivStar4);
            rateHolder.ivStar5 = view.findViewById(R.id.ivStar5);
            view.setTag(rateHolder);
        } else {
            rateHolder = (RateHolder) view.getTag();
        }

        rate = getItem(position);
        rateHolder.tvUsuario.setText(UserRepository.getInstance().getUser(rate.getIdUsuario()).getNombre());
        rateHolder.tvDate.setText(MalagaSportApplication.DATE_FORMAT.format(rate.getFechaEdicion().getTime()));
        rateHolder.tvComentario.setText(rate.getComentario());
        rateHolder.ivStar1.setImageResource(R.drawable.ic_no_fav_32dp);
        rateHolder.ivStar2.setImageResource(R.drawable.ic_no_fav_32dp);
        rateHolder.ivStar3.setImageResource(R.drawable.ic_no_fav_32dp);
        rateHolder.ivStar4.setImageResource(R.drawable.ic_no_fav_32dp);
        rateHolder.ivStar5.setImageResource(R.drawable.ic_no_fav_32dp);
        switch (rate.getEstrellas()) {
            case 5:
                rateHolder.ivStar5.setImageResource(R.drawable.ic_fav_32dp);
            case 4:
                rateHolder.ivStar4.setImageResource(R.drawable.ic_fav_32dp);
            case 3:
                rateHolder.ivStar3.setImageResource(R.drawable.ic_fav_32dp);
            case 2:
                rateHolder.ivStar2.setImageResource(R.drawable.ic_fav_32dp);
            case 1:
                rateHolder.ivStar1.setImageResource(R.drawable.ic_fav_32dp);
        }
        return view;
    }

    @Nullable
    @Override
    public Rate getItem(int position) {
        return (Rate) super.getItem(position);
    }

    private class RateHolder {
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
