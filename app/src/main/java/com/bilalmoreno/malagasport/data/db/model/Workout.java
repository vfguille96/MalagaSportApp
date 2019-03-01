package com.bilalmoreno.malagasport.data.db.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Workout {
    private int id;
    private String nombre;
    private String direccion;
    private double latitude;
    private double longitude;

    private ArrayList<Machine> maquinas;

    public Workout(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Machine> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(ArrayList<Machine> maquinas) {
        this.maquinas = maquinas;
    }

    public String getNiveles() {
        if (maquinas.size() == 0) {
            return "0";
        }
        int nivelMinimo = maquinas.get(0).getNivel();
        int nivelMaximo = maquinas.get(0).getNivel();
        for (Machine machine :
                maquinas) {
            if (machine.getNivel() < nivelMinimo) {
                nivelMinimo = machine.getNivel();
            } else if (machine.getNivel() > nivelMaximo) {
                nivelMaximo = machine.getNivel();
            }
        }
        if (nivelMaximo == nivelMinimo) {
            return String.valueOf(nivelMaximo);
        }
        return String.valueOf(nivelMinimo) + "-" + String.valueOf(nivelMaximo);
    }

    public static class OrdenAlfabeticoDescendente implements Comparator<Workout> {

        @Override
        public int compare(Workout workoutA, Workout workoutB) {
            int result = workoutB.nombre.compareToIgnoreCase(workoutA.nombre);
            if (result == 0) {
                result = workoutA.id - workoutB.id;
            }
            return result;
        }
    }

    public static class OrdenAlfabeticoAscendente implements Comparator<Workout> {

        @Override
        public int compare(Workout workoutA, Workout workoutB) {
            int result = workoutA.nombre.compareToIgnoreCase(workoutB.nombre);
            if (result == 0) {
                result = workoutA.id - workoutB.id;
            }
            return result;
        }
    }

    public static class OrdenMaquinasCountAscendente implements Comparator<Workout> {
        @Override
        public int compare(Workout workoutA, Workout workoutB) {
            return workoutA.getMaquinas().size() - workoutB.getMaquinas().size();
        }
    }

    public static class OrdenMaquinasCountDescendente implements Comparator<Workout> {
        @Override
        public int compare(Workout workoutA, Workout workoutB) {
            return workoutB.getMaquinas().size() - workoutA.getMaquinas().size();
        }
    }
}
