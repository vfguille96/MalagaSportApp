package com.bilalmoreno.malagasport.pojo;

import java.util.Objects;

public class ParametroBusqueda {
    int id;
    String nombre;

    public ParametroBusqueda(int id) {
        this.id = id;
    }

    public ParametroBusqueda(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public boolean equals(Object o) {
        return o instanceof ParametroBusqueda && id == ((ParametroBusqueda) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
