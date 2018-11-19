package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Instalacion;

import java.util.ArrayList;

public class InstalacionRepository {
    private ArrayList<Instalacion> instalaciones;
    private static InstalacionRepository repository;

    private InstalacionRepository() {
        instalaciones = new ArrayList<>();
        inicializar();
    }

    public static InstalacionRepository getRepository() {
        return repository;
    }

    public ArrayList<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    private void inicializar() {
        //TODO Inicializar InstalacionRepository
    }

    static {
        repository = new InstalacionRepository();
    }

    private void add(Instalacion instalacion) {
        instalaciones.add(instalacion);
    }

}
