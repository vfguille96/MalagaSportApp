package com.bilalmoreno.malagasport.data.db.model;


import java.util.Calendar;
import java.util.Comparator;
import java.util.Objects;

public class Event {
    private String id;
    private String nombre;
    private int idLugar;
    private String lugar;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private String horario;
    private String descripcion;
    private boolean accesoMovReducida;
    private String organizador;
    private int destinatarios;
    private String telefono;
    private String correo;
    private String web;

    public Event(String id) {
        this.id = id;
    }

    public Event(String id, String nombre, String lugar, Calendar fechaInicio, Calendar fechaFin, String horario, String descripcion, boolean accesoMovReducida, String organizador, int destinatarios) {
        this.id = id;
        this.nombre = nombre;
        this.idLugar = -1;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horario = horario;
        this.descripcion = descripcion;
        this.accesoMovReducida = accesoMovReducida;
        this.organizador = organizador;
        this.destinatarios = destinatarios;
    }

    public Event(String id, String nombre, int idLugar, Calendar fechaInicio, Calendar fechaFin, String horario, String descripcion, boolean accesoMovReducida, String organizador, int destinatarios) {
        this.id = id;
        this.nombre = nombre;
        this.idLugar = idLugar;
        this.lugar = "";
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horario = horario;
        this.descripcion = descripcion;
        this.accesoMovReducida = accesoMovReducida;
        this.organizador = organizador;
        this.destinatarios = destinatarios;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getAccesoMovReducida() {
        return accesoMovReducida;
    }

    public void setAccesoMovReducida(boolean accesoMovReducida) {
        this.accesoMovReducida = accesoMovReducida;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public int getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(int destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Event && id.equals(((Event) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class OrdenFechaDescendente implements Comparator<Event> {

        @Override
        public int compare(Event eventA, Event eventB) {
            int result = eventB.fechaInicio.compareTo(eventA.fechaInicio);
            if (result == 0) {
                result = eventA.horario.compareTo(eventB.horario);
            }
            return result;
        }
    }

    public static class OrdenFechaAscendente implements Comparator<Event> {

        @Override
        public int compare(Event eventA, Event eventB) {
            int result = eventA.fechaInicio.compareTo(eventB.fechaInicio);
            if (result == 0) {
                result = eventA.horario.compareTo(eventB.horario);
            }
            return result;
        }
    }
}
