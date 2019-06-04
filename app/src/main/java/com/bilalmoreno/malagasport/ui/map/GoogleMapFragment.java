package com.bilalmoreno.malagasport.ui.map;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;

import com.bilalmoreno.malagasport.util.Map;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.bilalmoreno.malagasport.util.Map.LATITUDE;
import static com.bilalmoreno.malagasport.util.Map.LONGITUDE;
import static com.bilalmoreno.malagasport.util.Map.ZOOM_FAR;
import static com.bilalmoreno.malagasport.util.Map.ZOOM_NEAR;

public class GoogleMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMapContract.View {

    public static final String TAG = "GoogleMapFragment";
    private GoogleMap map;
    private GoogleMapContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);

        presenter = new GoogleMapPresenter(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
     this.map = map;
     centerMap();
     presenter.load();
    }

    @Override
    public void centerMap() {
        // Posicionar el mapa en una localización y con un nivel de zoom
        LatLng latLng = new LatLng(LATITUDE, LONGITUDE);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_FAR));
    }

    @Override
    public void show(ArrayList<Installation> installations) {
        for (Installation installation :
                installations) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(installation.getLatitud(), installation.getLongitud()));
            markerOptions.title(installation.getNombre());
            markerOptions.snippet(String.format(installation.getDireccion()));
            map.addMarker(markerOptions);
        }
    }
}
