package com.bilalmoreno.malagasport.pojo;

import java.util.Comparator;
import java.util.Objects;

public class Maquina {
    private int id;
    private String nombre;
    private int nivel;
    private String funcion;
    private String desarollo;
    private String precauciones;

    public Maquina(int id) {
        this.id = id;
    }

    public Maquina(int id, String nombre, int nivel, String funcion, String desarollo, String precauciones) {
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

    public int getNivel() {
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

    public void setNivel(int nivel) {
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

    public static class OrdenNivelAscendente implements Comparator<Maquina> {

        @Override
        public int compare(Maquina maquinaA, Maquina maquinaB) {

            return maquinaA.getNivel() - maquinaB.getNivel();
        }
    }

    public static class OrdenNivelDescendente implements Comparator<Maquina> {

        @Override
        public int compare(Maquina maquinaA, Maquina maquinaB) {

            return maquinaB.getNivel() - maquinaA.getNivel();
        }
    }
}
