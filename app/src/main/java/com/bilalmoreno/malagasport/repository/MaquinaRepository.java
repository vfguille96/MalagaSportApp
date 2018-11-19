package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Maquina;

import java.util.ArrayList;

public class MaquinaRepository {
    private ArrayList<Maquina> maquinas;
    private static MaquinaRepository repository;

    static {
        repository = new MaquinaRepository();
    }

    private MaquinaRepository() {
        maquinas = new ArrayList<>();
        inicializar();
    }

    public static MaquinaRepository getRepository() {
        return repository;
    }

    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    private void inicializar() {
        //TODO Inicializar MaquinaRepository
    }

    private void add(Maquina maquina) {
        maquinas.add(maquina);
    }
}
