
package com.bilalmoreno.malagasport.data.service.model.workout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("serial")
    @Expose
    private Integer serial;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("timestamp")
    @Expose
    private Object timestamp;
    @SerializedName("begin")
    @Expose
    private Object begin;
    @SerializedName("end")
    @Expose
    private Object end;
    @SerializedName("altitudemode")
    @Expose
    private String altitudemode;
    @SerializedName("tessellate")
    @Expose
    private Integer tessellate;
    @SerializedName("extrude")
    @Expose
    private Integer extrude;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("draworder")
    @Expose
    private Integer draworder;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id_zonamusculacion")
    @Expose
    private String idZonamusculacion;
    @SerializedName("nomzonamusculacion")
    @Expose
    private String nomzonamusculacion;
    @SerializedName("ubicacion")
    @Expose
    private String ubicacion;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("id_maquina")
    @Expose
    private String idMaquina;
    @SerializedName("nommaquina")
    @Expose
    private String nommaquina;
    @SerializedName("nivel")
    @Expose
    private String nivel;
    @SerializedName("icono")
    @Expose
    private String icono;
    @SerializedName("funcion")
    @Expose
    private String funcion;
    @SerializedName("desarrollo")
    @Expose
    private String desarrollo;
    @SerializedName("precauciones")
    @Expose
    private String precauciones;
    @SerializedName("maquinafoto1")
    @Expose
    private String maquinafoto1;
    @SerializedName("maquinafoto2")
    @Expose
    private String maquinafoto2;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public Object getBegin() {
        return begin;
    }

    public void setBegin(Object begin) {
        this.begin = begin;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }

    public String getAltitudemode() {
        return altitudemode;
    }

    public void setAltitudemode(String altitudemode) {
        this.altitudemode = altitudemode;
    }

    public Integer getTessellate() {
        return tessellate;
    }

    public void setTessellate(Integer tessellate) {
        this.tessellate = tessellate;
    }

    public Integer getExtrude() {
        return extrude;
    }

    public void setExtrude(Integer extrude) {
        this.extrude = extrude;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getDraworder() {
        return draworder;
    }

    public void setDraworder(Integer draworder) {
        this.draworder = draworder;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdZonamusculacion() {
        return idZonamusculacion;
    }

    public void setIdZonamusculacion(String idZonamusculacion) {
        this.idZonamusculacion = idZonamusculacion;
    }

    public String getNomzonamusculacion() {
        return nomzonamusculacion;
    }

    public void setNomzonamusculacion(String nomzonamusculacion) {
        this.nomzonamusculacion = nomzonamusculacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNommaquina() {
        return nommaquina;
    }

    public void setNommaquina(String nommaquina) {
        this.nommaquina = nommaquina;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(String desarrollo) {
        this.desarrollo = desarrollo;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    public String getMaquinafoto1() {
        return maquinafoto1;
    }

    public void setMaquinafoto1(String maquinafoto1) {
        this.maquinafoto1 = maquinafoto1;
    }

    public String getMaquinafoto2() {
        return maquinafoto2;
    }

    public void setMaquinafoto2(String maquinafoto2) {
        this.maquinafoto2 = maquinafoto2;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

}
