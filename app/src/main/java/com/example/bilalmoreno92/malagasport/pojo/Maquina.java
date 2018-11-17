package com.example.bilalmoreno92.malagasport.pojo;

class Maquina {
    private int id;
    private String nombre;
    private String dificultad;
    private String funcion;
    private String desarollo;
    private String precauciones;

    public Maquina(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Maquina(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getDesarollo() {
        return desarollo;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setDesarollo(String desarollo) {
        this.desarollo = desarollo;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }
}
