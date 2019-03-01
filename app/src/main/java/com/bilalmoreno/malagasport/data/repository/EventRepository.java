package com.bilalmoreno.malagasport.data.repository;

import com.bilalmoreno.malagasport.data.db.model.Event;

import java.util.ArrayList;
import java.util.Calendar;

public class EventRepository {
    private static EventRepository repository;

    static {
        repository = new EventRepository();
    }

    private ArrayList<Event> events;

    private EventRepository() {
        events = new ArrayList<>();
        inicializar();
    }

    public static EventRepository getRepository() {
        return repository;
    }

    private void inicializar() {
        //TODO Inincializar EventRepository
        add(new Event("01uni-mad", "Amistoso Unicaja - Madrid", 1, Calendar.getInstance(), Calendar.getInstance(), "10:00 - 15:00", "Partido amistoso entre el equipo local de baloncestode Málaga y el de Madrid", true, "Federación de Baloncesto de Málaga", 4));
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    private void add(Event event) {
        events.add(event);
    }
}
