package com.bilalmoreno.malagasport.pojo;

import java.util.Objects;

public class Maquina {
    private int id;
    private String nombre;
    private String nivel;
    private String funcion;
    private String desarollo;
    private String precauciones;

    public Maquina(int id) {
        this.id = id;
    }

    public Maquina(int id, String nombre, String nivel, String funcion, String desarollo, String precauciones) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.funcion = funcion;
        this.desarollo = desarollo;
        this.precauciones = precauciones;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getDesarollo() {
        return desarollo;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setDesarollo(String desarollo) {
        this.desarollo = desarollo;
    }

    public void setPrecauciones(String precauciones) { this.precauciones = precauciones; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Maquina && ((Maquina) o).getId() == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
