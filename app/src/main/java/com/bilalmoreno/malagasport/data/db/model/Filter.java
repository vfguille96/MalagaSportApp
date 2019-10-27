package com.bilalmoreno.malagasport.data.db.model;

import androidx.annotation.NonNull;

import com.bilalmoreno.malagasport.data.repository.TrackRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class Filter {
    private String nombre;
    private ArrayList<Integer> parametrosBusqueda;

    public Filter(String nombre) {
        this.nombre = nombre;
        this.parametrosBusqueda = new ArrayList<>();
    }

    public Filter(String nombre, ArrayList<Integer> parametrosBusqueda) {
        this.nombre = nombre;
        this.parametrosBusqueda = parametrosBusqueda;
    }

    public void add(int parametroBusqueda) {
        if (!parametrosBusqueda.contains(parametroBusqueda)) {
            parametrosBusqueda.add(parametroBusqueda);
        }
    }

    //TODO separar en dos métodos (uno para instalaciones y otro para workouts)
//    public int matches(Workout installation) {
//        int matches = 0;
//        for (int parametro :
//                parametrosBusqueda) {
//            switch (parametro) {
//                case SearchParameter.ILUMINACION:
//                    if (matchIluminacion(installation)) {
//                        matches++;
//                    }
//                    break;
//                case SearchParameter.MOV_REDUCIDA:
//                    if (matchAccesoMovReducida(installation)) {
//                        matches++;
//                    }
//                    break;
//                case SearchParameter.NIVEL_1:
//                    if (matchNivel(installation, 1)) {
//                        matches++;
//                    }
//                    break;
//                case SearchParameter.NIVEL_2:
//                    if (matchNivel(installation, 2)) {
//                        matches++;
//                    }
//                    break;
//                case SearchParameter.NIVEL_3:
//                    if (matchNivel(installation, 3)) {
//                        matches++;
//                    }
//                    break;
//                case SearchParameter.NIVEL_4:
//                    if (matchNivel(installation, 4)) {
//                        matches++;
//                    }
//                    break;
//                default: //Actividades deportivas
//                    if (matchActividadDeportiva(installation, parametro)) {
//                        matches++;
//                    }
//                    break;
//
//            }
//        }
//        return matches;
//    }

    private boolean matchNivel(@NonNull Workout workout, int nivel) {
        ArrayList<Machine> maquinas = workout.getMaquinas();
        for (Machine machine :
                maquinas) {
            if (machine.getNivel() == nivel) {
                return true;
            }
        }
        return false;
    }

    private boolean matchActividadDeportiva(@NonNull Installation installation, int actividadDeportiva) {
        ArrayList<Integer> pistas = installation.getPistas();
        for (int idPista :
                pistas) {
            if (TrackRepository.getInstance().get(idPista).getActividadDeportiva() == actividadDeportiva) {
                return true;
            }
        }
        return false;
    }

    private boolean matchAccesoMovReducida(@NonNull Installation installation) {
        return installation.getAccesoMovReducida();
    }

    private boolean matchIluminacion(@NonNull Installation installation) {
        for (int idPista :
                installation.getPistas()) {
            if (TrackRepository.getInstance().get(idPista).getIluminacion()) {
                return true;
            }
        }
        return false;
    }

    public static class OrdenAlfabeticoDescendente implements Comparator<Filter> {

        @Override
        public int compare(Filter filterA, Filter filterB) {
            int result = filterB.nombre.compareToIgnoreCase(filterA.nombre);
            if (result == 0) {
                result = filterA.parametrosBusqueda.size() - filterB.parametrosBusqueda.size();
            }
            return result;
        }
    }

    public static class OrdenAlfabeticoAscendente implements Comparator<Filter> {

        @Override
        public int compare(Filter filterA, Filter filterB) {
            int result = filterA.nombre.compareToIgnoreCase(filterB.nombre);
            if (result == 0) {
                result = filterA.parametrosBusqueda.size() - filterB.parametrosBusqueda.size();
            }
            return result;
        }
    }
}
