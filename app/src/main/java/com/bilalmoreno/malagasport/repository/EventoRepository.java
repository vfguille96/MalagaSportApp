package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Evento;

import java.util.ArrayList;

public class EventoRepository {
    private ArrayList<Evento> eventos;
    private static EventoRepository repository;

    private EventoRepository() {
        eventos = new ArrayList<>();
        inicializar();
    }

    static {
        repository = new EventoRepository();
    }

    private void inicializar() {
        //TODO Inincializar EventoRepository
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public static EventoRepository getRepository() {
        return repository;
    }

    private void add(Evento evento){
        eventos.add(evento);
    }
}
