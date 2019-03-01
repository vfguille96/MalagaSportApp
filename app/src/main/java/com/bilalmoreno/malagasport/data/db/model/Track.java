package com.bilalmoreno.malagasport.data.db.model;

import java.util.Comparator;
import java.util.Objects;

public class Track {
    private int id;
    private String nombre;
    private boolean iluminacion;
    private String dimensiones;
    private int actividadDeportiva;
    private String pavimento;

    public Track(int id) {
        this.id = id;
    }

    public Track(int id, String nombre, boolean iluminacion, String dimensiones, int actividadDeportiva) {
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
        return o instanceof Track && (iluminacion == ((Track) o).iluminacion &&
                nombre.equals(((Track) o).nombre) &&
                dimensiones.equals(((Track) o).dimensiones) &&
                actividadDeportiva == ((Track) o).actividadDeportiva &&
                pavimento.equals(((Track) o).pavimento));
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, iluminacion, dimensiones, actividadDeportiva, pavimento);
    }
}
