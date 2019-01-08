package com.bilalmoreno.malagasport.data.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Valoration implements Serializable {
    public static final String TAG = "valoracion";
    private int idInstlacion;
    private String idUsuario;
    private Date fecha;
    private Date fechaEdicion;
    private int estrellas;
    private String comentario;

    public Valoration(int idInstlacion, String idUsuario) {
        this.idInstlacion = idInstlacion;
        this.idUsuario = idUsuario;
    }

    public Valoration(int idInstlacion, String idUsuario, Date fecha, int estrellas, String comentario) {
        this.idInstlacion = idInstlacion;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.fechaEdicion = fecha;
        this.estrellas = estrellas;
        this.comentario = comentario;
    }

    public int getIdInstlacion() {
        return idInstlacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Date fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Valoration)) return false;
        Valoration that = (Valoration) o;
        return idInstlacion == that.idInstlacion &&
                Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstlacion, idUsuario);
    }
}
