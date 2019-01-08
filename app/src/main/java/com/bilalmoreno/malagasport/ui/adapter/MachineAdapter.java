package com.bilalmoreno.malagasport.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Machine;

import java.util.ArrayList;

public class MachineAdapter extends ArrayAdapter {

    ArrayList<Machine> machines;
    MachineListListener listener;

    public MachineAdapter(@NonNull Context context, MachineListListener listener) {
        super(context, R.layout.item_maquina);
        this.listener = listener;
        machines = new ArrayList<>();
//        Collections.sort(machines, orden);
//        addAll(machines);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Machine machine;
        MaquinaHolder maquinaHolder;

        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_maquina, null);
            maquinaHolder = new MaquinaHolder();
            maquinaHolder.tvNombre = view.findViewById(R.id.tvNombre);
            maquinaHolder.tvNivelMaquina = view.findViewById(R.id.tvNivelMaquina);
            view.setTag(maquinaHolder);
        } else {
            maquinaHolder = (MaquinaHolder) view.getTag();
        }

        machine = (Machine) getItem(position);
        maquinaHolder.tvNombre.setText(machine.getNombre());
        maquinaHolder.tvNivelMaquina.setText(String.valueOf(machine.getNivel()));

        return view;
    }

    public interface MachineListListener extends AdapterView.OnItemClickListener {
    }

    private class MaquinaHolder {
        TextView tvNombre;
        TextView tvNivelMaquina;
    }
}
