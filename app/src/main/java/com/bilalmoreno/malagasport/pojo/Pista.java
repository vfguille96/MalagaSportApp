package com.bilalmoreno.malagasport.pojo;

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

    public Pista(int id, String nombre, boolean iluminacion, String dimensiones) {
        this.id = id;
        this.nombre = nombre;
        this.iluminacion = iluminacion;
        this.dimensiones = dimensiones;
    }

    public int getId() { return id; }

    public String getNombre() {
        return nombre;
    }

    public boolean getIluminacion() {
        return iluminacion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public int getActividadDeportiva() { return actividadDeportiva; }

    public String getPavimento() {
        return pavimento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIluminacion(boolean iluminacion) { this.iluminacion = iluminacion; }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public void setActividadDeportiva(int actividadDeportiva) {
        this.actividadDeportiva = actividadDeportiva;
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
}
