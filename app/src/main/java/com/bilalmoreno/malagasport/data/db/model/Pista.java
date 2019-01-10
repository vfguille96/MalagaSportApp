package com.bilalmoreno.malagasport.data.db.model;

import java.util.Comparator;
import java.util.Objects;

public class Pista {
    private int id;
    private String nombre;
    private boolean iluminacion;
    private String dimensiones;
    private int actividadDeportiva;
    private String pavimento;

    public Pista(int id) {
        this.id = id;
    }

    public Pista(int id, String nombre, boolean iluminacion, String dimensiones, int actividadDeportiva) {
        this.id = id;
        this.nombre = nombre;
        this.iluminacion = iluminacion;
        this.dimensiones = dimensiones;
        this.actividadDeportiva = actividadDeportiva;
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

    public boolean getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(boolean iluminacion) {
        this.iluminacion = iluminacion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public int getActividadDeportiva() {
        return actividadDeportiva;
    }

    public void setActividadDeportiva(int actividadDeportiva) {
        this.actividadDeportiva = actividadDeportiva;
    }

    public String getPavimento() {
        return pavimento;
    }

    public void setPavimento(String pavimento) {
        this.pavimento = pavimento;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Pista && (iluminacion == ((Pista) o).iluminacion &&
                nombre.equals(((Pista) o).nombre) &&
                dimensiones.equals(((Pista) o).dimensiones) &&
                actividadDeportiva == ((Pista) o).actividadDeportiva &&
                pavimento.equals(((Pista) o).pavimento));
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, iluminacion, dimensiones, actividadDeportiva, pavimento);
    }

    public static class OrdenNivelDescendente implements Comparator<Installation> {

        @Override
        public int compare(Installation installationA, Installation installationB) {
            return installationB.getNiveles().compareTo(installationA.getNiveles());
        }
    }

    public static class OrdenNivelAscendente implements Comparator<Installation> {

        @Override
        public int compare(Installation installationA, Installation installationB) {
            return installationA.getNiveles().compareTo(installationB.getNiveles());
        }
    }
}
