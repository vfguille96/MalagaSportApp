package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Valoracion;

import java.util.ArrayList;

public class ValoracionRepository {
    private ArrayList<Valoracion> valoraciones;
    private static ValoracionRepository repository;

    private ValoracionRepository() {
        valoraciones = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        //TODO Inicializar ValoracionRepository
    }

    static {
        repository = new ValoracionRepository();
    }

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public static ValoracionRepository getRepository() {
        return repository;
    }

    private void add(Valoracion valoracion){
        valoraciones.add(valoracion);
    }
}
