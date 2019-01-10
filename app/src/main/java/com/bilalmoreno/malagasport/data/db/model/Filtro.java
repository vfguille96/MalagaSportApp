package com.bilalmoreno.malagasport.data.db.model;

import android.support.annotation.NonNull;

import com.bilalmoreno.malagasport.data.db.repository.MachineRepository;
import com.bilalmoreno.malagasport.data.db.repository.PistaRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class Filtro {
    private String nombre;
    private ArrayList<Integer> parametrosBusqueda;

    public Filtro(String nombre) {
        this.nombre = nombre;
        this.parametrosBusqueda = new ArrayList<>();
    }

    public Filtro(String nombre, ArrayList<Integer> parametrosBusqueda) {
        this.nombre = nombre;
        this.parametrosBusqueda = parametrosBusqueda;
    }

    public void add(int parametroBusqueda) {
        if (!parametrosBusqueda.contains(parametroBusqueda)) {
            parametrosBusqueda.add(parametroBusqueda);
        }
    }

    public int matches(Installation installation) {
        int matches = 0;
        for (int parametro :
                parametrosBusqueda) {
            switch (parametro) {
                case ParametroBusqueda.ILUMINACION:
                    if (matchIluminacion(installation)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.MOV_REDUCIDA:
                    if (matchAccesoMovReducida(installation)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_1:
                    if (matchNivel(installation, 1)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_2:
                    if (matchNivel(installation, 2)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_3:
                    if (matchNivel(installation, 3)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_4:
                    if (matchNivel(installation, 4)) {
                        matches++;
                    }
                    break;
                default: //Actividades deportivas
                    if (matchActividadDeportiva(installation, parametro)) {
                        matches++;
                    }
                    break;

            }
        }
        return matches;
    }

    private boolean matchNivel(@NonNull Installation installation, int nivel) {
        ArrayList<Integer> maquinas = installation.getMaquinas();
        for (int idMaquina :
                maquinas) {
            if (MachineRepository.getRepository().getMaquina(idMaquina).getNivel() == nivel) {
                return true;
            }
        }
        return false;
    }

    private boolean matchActividadDeportiva(@NonNull Installation installation, int actividadDeportiva) {
        ArrayList<Integer> pistas = installation.getPistas();
        for (int idPista :
                pistas) {
            if (PistaRepository.getRepository().getPista(idPista).getActividadDeportiva() == actividadDeportiva) {
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
            if (PistaRepository.getRepository().getPista(idPista).getIluminacion()) {
                return true;
            }
        }
        return false;
    }

    public static class OrdenAlfabeticoDescendente implements Comparator<Filtro> {

        @Override
        public int compare(Filtro filtroA, Filtro filtroB) {
            int result = filtroB.nombre.compareToIgnoreCase(filtroA.nombre);
            if (result == 0) {
                result = filtroA.parametrosBusqueda.size() - filtroB.parametrosBusqueda.size();
            }
            return result;
        }
    }

    public static class OrdenAlfabeticoAscendente implements Comparator<Filtro> {

        @Override
        public int compare(Filtro filtroA, Filtro filtroB) {
            int result = filtroA.nombre.compareToIgnoreCase(filtroB.nombre);
            if (result == 0) {
                result = filtroA.parametrosBusqueda.size() - filtroB.parametrosBusqueda.size();
            }
            return result;
        }
    }
}
