package com.bilalmoreno.malagasport.data.db.repository;

import com.bilalmoreno.malagasport.data.db.model.Pista;

import java.util.ArrayList;

public class PistaRepository {
    private static PistaRepository repository;

    static {
        repository = new PistaRepository();
    }

    private ArrayList<Pista> pistas;

    private PistaRepository() {
        pistas = new ArrayList<>();
        inicializar();
    }

    public static PistaRepository getRepository() {
        return repository;
    }

    private void inicializar() {
        //TODO Inicializar PistaRepository
        add(new Pista(1, "Basket", true, "30x25 m"));
        add(new Pista(2, "Basket", false, "30x25 m"));
        add(new Pista(3, "Futbol sala", true, "60x30 m"));
        add(new Pista(4, "Futbol sala", false, "60x30 m"));
        add(new Pista(5, "Volleyball", true, "6x3 m"));
        add(new Pista(6, "Volley-Playa", false, "6x3 m"));
    }

    private void add(Pista pista) {
        pistas.add(pista);
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
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
