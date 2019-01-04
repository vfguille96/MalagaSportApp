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
        add(new Pista(1, "Basket", true, "30x25 m"));
        add(new Pista(2, "Basket", false, "30x25 m"));
        add(new Pista(3, "Futbol sala", true, "60x30 m"));
        add(new Pista(4, "Futbol sala", false, "60x30 m"));
        add(new Pista(5,"Volleyball",true, "6x3 m"));
        add(new Pista(6, "Volley-Playa",false,"6x3 m"));
    }

    private void add(Pista pista) {
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

    public Pista getPista(int idPista) {
        Pista pistaBuscada = new Pista(idPista);
        for (Pista pista :
                pistas) {
            if (pista.getId() == pistaBuscada.getId()) {
                return pista;
            }
        }
        return null;
    }
}
