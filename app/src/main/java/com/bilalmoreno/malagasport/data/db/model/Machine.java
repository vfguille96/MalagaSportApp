package com.bilalmoreno.malagasport.data.db.model;

import java.util.Comparator;
import java.util.Objects;

public class Machine {
    private int id;
    private String nombre;
    private int nivel;
    private String funcion;
    private String desarollo;
    private String precauciones;

    public Machine(int id) {
        this.id = id;
    }

    public Machine(int id, String nombre, int nivel, String funcion, String desarollo, String precauciones) {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getDesarollo() {
        return desarollo;
    }

    public void setDesarollo(String desarollo) {
        this.desarollo = desarollo;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Machine && ((Machine) o).getId() == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class OrdenNivelAscendente implements Comparator<Machine> {

        @Override
        public int compare(Machine machineA, Machine machineB) {
            if (machineA.getNivel() - machineB.getNivel() != 0) {
                return machineA.getNivel() - machineB.getNivel();
            }
            return machineA.getNombre().compareToIgnoreCase(machineB.getNombre());
        }
    }

    public static class OrdenNivelDescendente implements Comparator<Machine> {

        @Override
        public int compare(Machine machineA, Machine machineB) {
            if (machineB.getNivel() - machineA.getNivel() != 0) {
                return machineB.getNivel() - machineA.getNivel();
            }
            return machineA.getNombre().compareToIgnoreCase(machineB.getNombre());

        }
    }
}
