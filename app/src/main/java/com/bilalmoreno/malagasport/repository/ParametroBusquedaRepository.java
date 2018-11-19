package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.ParametroBusqueda;

import java.util.ArrayList;

public class ParametroBusquedaRepository {
    private ArrayList<ParametroBusqueda> parametrosBusqueda;
    private static ParametroBusquedaRepository repository;

    private ParametroBusquedaRepository() {
        parametrosBusqueda = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        //TODO Inicializar ParametroBusquedaRepository
    }

    static {
        repository = new ParametroBusquedaRepository();
    }

    public ArrayList<ParametroBusqueda> getParametrosBusqueda() {
        return parametrosBusqueda;
    }

    public static ParametroBusquedaRepository getRepository() {
        return repository;
    }

    private void add(ParametroBusqueda parametroBusqueda){
        parametrosBusqueda.add(parametroBusqueda);
    }
}
