package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Pista;

import java.util.ArrayList;

public class PistaRepository {
    private ArrayList<Pista> pistas;
    private static PistaRepository repository;

    private PistaRepository() {
        pistas = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        //TODO Inicializar PistaRepository
    }

    private void add(Pista pista){
        pistas.add(pista);
    }

    static {
        repository = new PistaRepository();
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public static PistaRepository getRepository() {
        return repository;
    }
}
