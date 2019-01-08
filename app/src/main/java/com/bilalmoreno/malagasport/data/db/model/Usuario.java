package com.bilalmoreno.malagasport.data.db.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Usuario {
    public static final String TAG = "usuario";
    private String id;
    private String email;
    private String nombre;
    private Calendar fechaNacimiento;
    private String fotoURL;
    private ArrayList<Integer> favoritos; //Instalaciones favoritas
    private ArrayList<Integer> seguidos; //Eventos seguidos
    private ArrayList<String> filtros; //Filtros guardados

    public Usuario(String id) {
        this.id = id;
        favoritos = new ArrayList<>();
        seguidos = new ArrayList<>();
        filtros = new ArrayList<>();
    }

    public Usuario(String id, String email, String nombre, Calendar fechaNacimiento) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        favoritos = new ArrayList<>();
        seguidos = new ArrayList<>();
        filtros = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public ArrayList<Integer> getFavoritos() {
        return favoritos;
    }

    public ArrayList<Integer> getSeguidos() {
        return seguidos;
    }

    public ArrayList<String> getFiltros() {
        return filtros;
    }

    public void addFavorito(int idInstalacion) {
        favoritos.add(idInstalacion);
    }

    public void removeFavorito(int idInstalacion) {
        favoritos.remove(idInstalacion);
    }

    public void addSeguido(Integer idEvento) {
        seguidos.add(idEvento);
    }

    public void removeSeguido(Integer idEvento) {
        seguidos.remove(idEvento);
    }

    public void addFiltro(String nombre) {
        filtros.add(nombre);
    }

    public void removeFiltro(String nombre) {
        filtros.remove(nombre);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Usuario && id.equals(((Usuario) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
