package com.bilalmoreno.malagasport.pojo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Valoracion {
    private int idInstlacion;
    private int idUsuario;
    private LocalDateTime fecha;
    private int estrellas;
    private String comentario;

    public Valoracion(int idInstlacion, int idUsuario) {
        this.idInstlacion = idInstlacion;
        this.idUsuario = idUsuario;
    }

    public Valoracion(int idInstlacion, int idUsuario, LocalDateTime fecha, int estrellas, String comentario) {
        this.idInstlacion = idInstlacion;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.estrellas = estrellas;
        this.comentario = comentario;
    }

    public int getIdInstlacion() {
        return idInstlacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
        return o instanceof Valoracion
                && idInstlacion == ((Valoracion) o).idInstlacion
                && idUsuario == ((Valoracion) o).idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstlacion, idUsuario);
    }
}