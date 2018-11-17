package com.example.bilalmoreno92.malagasport.pojo;

class Pista {
    private String nombre;
    private boolean iluminacion;
    private String dimensiones;
    private ActividadDeportiva actividadDeportiva;
    private String pavimento;

    public Pista(String nombre, boolean iluminacion, String dimensiones) {
        this.nombre = nombre;
        this.iluminacion = iluminacion;
        this.dimensiones = dimensiones;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isIluminacion() {
        return iluminacion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public ActividadDeportiva getActividadDeportiva() {
        return actividadDeportiva;
    }

    public String getPavimento() {
        return pavimento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIluminacion(boolean iluminacion) {
        this.iluminacion = iluminacion;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public void setActividadDeportiva(ActividadDeportiva actividadDeportiva) {
        this.actividadDeportiva = actividadDeportiva;
    }

    public void setPavimento(String pavimento) {
        this.pavimento = pavimento;
    }
}
