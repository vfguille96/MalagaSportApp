package com.bilalmoreno.malagasport.data.service.model.installation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class INFOESP {

    @SerializedName("Espacio_deportivo")
    @Expose
    private String espacioDeportivo;
    @SerializedName("Iluminacion")
    @Expose
    private String iluminacion;
    @SerializedName("Actividad_deportiva")
    @Expose
    private String actividadDeportiva;
    @SerializedName("Dimensiones_en_metros")
    @Expose
    private String dimensionesEnMetros;
    @SerializedName("Tipo_de_pavimento")
    @Expose
    private String tipoDePavimento;

    public String getEspacioDeportivo() {
        return espacioDeportivo;
    }

    public void setEspacioDeportivo(String espacioDeportivo) {
        this.espacioDeportivo = espacioDeportivo;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public String getActividadDeportiva() {
        return actividadDeportiva;
    }

    public void setActividadDeportiva(String actividadDeportiva) {
        this.actividadDeportiva = actividadDeportiva;
    }

    public String getDimensionesEnMetros() {
        return dimensionesEnMetros;
    }

    public void setDimensionesEnMetros(String dimensionesEnMetros) {
        this.dimensionesEnMetros = dimensionesEnMetros;
    }

    public String getTipoDePavimento() {
        return tipoDePavimento;
    }

    public void setTipoDePavimento(String tipoDePavimento) {
        this.tipoDePavimento = tipoDePavimento;
    }

}
