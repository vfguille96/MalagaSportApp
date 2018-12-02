package com.bilalmoreno.malagasport.pojo;

import com.bilalmoreno.malagasport.repository.MaquinaRepository;
import com.bilalmoreno.malagasport.repository.PistaRepository;

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
    private ArrayList<Integer> pistas;
    private ArrayList<Integer> maquinas;

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
        this.nombre = "";
        this.direccion = "";
        this.latitud = 0;
        this.longitud = 0;
        this.tarjetaJoven = false;
        this.accesoMovReducida = false;
        pistas = new ArrayList<>();
        maquinas = new ArrayList<>();
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

    public boolean getTarjetaJoven() {
        return tarjetaJoven;
    }

    public boolean getAccesoMovReducida() {
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

    public ArrayList<Integer> getPistas() {
        return pistas;
    }

    public ArrayList<Integer> getMaquinas() {
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

    public void addPista(Integer idPista){
        pistas.add(idPista);
    }

    public void addMaquina(Integer idMaquina){
        maquinas.add(idMaquina);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Instalacion && this.id == ((Instalacion) object).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getNiveles() {
        if (maquinas.size() == 0) {
            return "0";
        }
        int nivelMinimo = MaquinaRepository.getRepository().getMaquina(maquinas.get(0)).getNivel();
        int nivelMaximo = MaquinaRepository.getRepository().getMaquina(maquinas.get(0)).getNivel();
        for (int idMaquina :
                maquinas) {
            Maquina maquina = MaquinaRepository.getRepository().getMaquina(idMaquina);
            if (maquina.getNivel() < nivelMinimo) {
                nivelMinimo = maquina.getNivel();
            } else if (maquina.getNivel() > nivelMaximo) {
                nivelMaximo = maquina.getNivel();
            }
        }
        if (nivelMaximo == nivelMinimo) {
            return String.valueOf(nivelMaximo);
        }
        return String.valueOf(nivelMinimo) + "-" + String.valueOf(nivelMaximo);
    }

    public boolean getIluminacion() {
        for (int idPista :
                pistas) {
            Pista pista = PistaRepository.getRepository().getPista(idPista);
            if (pista.getIluminacion()) {
                return true;
            }
        }
        return false;
    }
}
