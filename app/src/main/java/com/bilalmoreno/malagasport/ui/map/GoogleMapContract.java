package com.bilalmoreno.malagasport.ui.map;

import com.bilalmoreno.malagasport.data.db.model.Installation;

import java.util.ArrayList;

public interface GoogleMapContract {
    interface View {
        void centerMap();

        void show(ArrayList<Installation> installations);
    }

    interface Presenter {

        void load();
    }
}
