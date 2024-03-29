package com.bilalmoreno.malagasport.data.db.model;

import com.bilalmoreno.malagasport.data.repository.TrackRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Installation {
    public static final String TAG = "instlacion";
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

    public Installation(int id, String nombre, String direccion, double latitud, double longitud, boolean tarjetaJoven, boolean accesoMovReducida) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tarjetaJoven = tarjetaJoven;
        this.accesoMovReducida = accesoMovReducida;
        pistas = new ArrayList<>();
    }

    public Installation(int id) {
        this.id = id;
        this.nombre = "";
        this.direccion = "";
        this.latitud = 0;
        this.longitud = 0;
        this.tarjetaJoven = false;
        this.accesoMovReducida = false;
        pistas = new ArrayList<>();
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public boolean getTarjetaJoven() {
        return tarjetaJoven;
    }

    public void setTarjetaJoven(boolean tarjetaJoven) {
        this.tarjetaJoven = tarjetaJoven;
    }

    public boolean getAccesoMovReducida() {
        return accesoMovReducida;
    }

    public void setAccesoMovReducida(boolean accesoMovReducida) {
        this.accesoMovReducida = accesoMovReducida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public ArrayList<Integer> getPistas() {
        return pistas;
    }


    public void addPista(Integer idPista) {
        pistas.add(idPista);
    }


    @Override
    public boolean equals(Object object) {
        return object instanceof Installation && this.id == ((Installation) object).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean getIluminacion() {
        for (int idPista :
                pistas) {
            Track track = TrackRepository.getInstance().get(idPista);
            if (track.getIluminacion()) {
                return true;
            }
        }
        return false;
    }

    public static class OrdenAlfabeticoDescendente implements Comparator<Installation> {

        @Override
        public int compare(Installation installationA, Installation installationB) {
            int result = installationB.nombre.compareToIgnoreCase(installationA.nombre);
            if (result == 0) {
                result = installationA.id - installationB.id;
            }
            return result;
        }
    }

    public static class OrdenAlfabeticoAscendente implements Comparator<Installation> {

        @Override
        public int compare(Installation installationA, Installation installationB) {
            int result = installationA.nombre.compareToIgnoreCase(installationB.nombre);
            if (result == 0) {
                result = installationA.id - installationB.id;
            }
            return result;
        }
    }
}
