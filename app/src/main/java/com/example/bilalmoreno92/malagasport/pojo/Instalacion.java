package com.example.bilalmoreno92.malagasport.pojo;

import java.util.ArrayList;
import java.util.Objects;

public class Instalacion {
    private int id;
    private String nombre;
    private String direccion;
    private double latitud;
    private double longitud;
    private boolean tarjetaJoven;
    private boolean accesoMovReducida;
    private String descripcion;
    private String web;
    private String email;
    private String telefono;
    private String horario;
    private String precio;
    private ArrayList<Pista> pistas;
    private ArrayList<Maquina> maquinas;

    public Instalacion(int id, String nombre, String direccion, double latitud, double longitud, boolean tarjetaJoven, boolean accesoMovReducida) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tarjetaJoven = tarjetaJoven;
        this.accesoMovReducida = accesoMovReducida;
        pistas = new ArrayList<>();
        maquinas = new ArrayList<>();
    }

    public Instalacion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public boolean isTarjetaJoven() {
        return tarjetaJoven;
    }

    public boolean isAccesoMovReducida() {
        return accesoMovReducida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getWeb() {
        return web;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getHorario() {
        return horario;
    }

    public String getPrecio() {
        return precio;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setTarjetaJoven(boolean tarjetaJoven) {
        this.tarjetaJoven = tarjetaJoven;
    }

    public void setAccesoMovReducida(boolean accesoMovReducida) {
        this.accesoMovReducida = accesoMovReducida;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void add(Pista pista){
        pistas.add(pista);
    }

    public void add(Maquina maquina){
        maquinas.add(maquina);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Instalacion ? this.id == ((Instalacion) object).id : false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
