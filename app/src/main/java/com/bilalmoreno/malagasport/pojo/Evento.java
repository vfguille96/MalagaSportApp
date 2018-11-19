package com.bilalmoreno.malagasport.pojo;


import java.time.LocalDate;
import java.util.Objects;

public class Evento {
    private String id;
    private String nombre;
    private int idLugar;
    private String lugar;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String horario;
    private String descripcion;
    private boolean accesoMovReducida;
    private String organizador;
    private String destinatarios;
    private String telefono;
    private String correo;
    private String web;

    public Evento(String id) {
        this.id = id;
    }

    public Evento(String id, String nombre, int idLugar, String lugar, LocalDate fechaInicio, LocalDate fechaFin, String horario, String descripcion, boolean accesoMovReducida, String organizador, String destinatarios) {
        this.id = id;
        this.nombre = nombre;
        this.idLugar = idLugar;
        this.lugar = lugar;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
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

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
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
        return o instanceof Evento &&  id.equals(((Evento) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
