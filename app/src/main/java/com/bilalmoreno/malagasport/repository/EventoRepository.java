package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Evento;

import java.util.ArrayList;
import java.util.Calendar;

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
        add(new Evento("01uni-mad", "Amistoso Unicaja - Madrid", 1, Calendar.getInstance(), Calendar.getInstance(), "10:00 - 15:00", "Partido amistoso entre el equipo local de baloncestode Málaga y el de Madrid", true, "Federación de Baloncesto de Málaga",  4));
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
