package com.bilalmoreno.malagasport.pojo;

import android.support.annotation.NonNull;

import com.bilalmoreno.malagasport.repository.MaquinaRepository;
import com.bilalmoreno.malagasport.repository.PistaRepository;

import java.util.ArrayList;

public class Filtro {
    private String nombre;
    private ArrayList<ParametroBusqueda> parametrosBusqueda;

    public Filtro(String nombre) {
        this.nombre = nombre;
        this.parametrosBusqueda = new ArrayList<>();
    }

    public Filtro(String nombre, ArrayList<ParametroBusqueda> parametrosBusqueda) {
        this.nombre = nombre;
        this.parametrosBusqueda = parametrosBusqueda;
    }

    public void add(ParametroBusqueda parametroBusqueda) {
        if (!parametrosBusqueda.contains(parametroBusqueda)) {
            parametrosBusqueda.add(parametroBusqueda);
        }
    }

    public int matches(Instalacion instalacion) {
        int matches = 0;
        for (ParametroBusqueda parametro :
                parametrosBusqueda) {
            switch (parametro.getId()) {
                case ParametroBusqueda.ILUMINACION:
                    if (matchIluminacion(instalacion)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.MOV_REDUCIDA:
                    if (matchAccesoMovReducida(instalacion)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_1:
                    if (matchNivel(instalacion, 1)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_2:
                    if (matchNivel(instalacion, 2)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_3:
                    if (matchNivel(instalacion, 3)) {
                        matches++;
                    }
                    break;
                case ParametroBusqueda.NIVEL_4:
                    if (matchNivel(instalacion, 4)) {
                        matches++;
                    }
                    break;
                default: //Actividades deportivas
                    if (matchActividadDeportiva(instalacion, parametro.getId())) {
                        matches++;
                    }
                    break;

            }
        }
        return matches;
    }

    private boolean matchNivel(@NonNull Instalacion instalacion, int nivel) {
        ArrayList<Integer> maquinas = instalacion.getMaquinas();
        for (int idMaquina :
                maquinas) {
            if (MaquinaRepository.getRepository().getMaquina(idMaquina).getNivel() == nivel) {
                return true;
            }
        }
        return false;
    }

    private boolean matchActividadDeportiva(@NonNull Instalacion instalacion, int actividadDeportiva) {
        ArrayList<Integer> pistas = instalacion.getPistas();
        for (int idPista :
                pistas) {
            if (PistaRepository.getRepository().getPista(idPista).getActividadDeportiva() == actividadDeportiva) {
                return true;
            }
        }
        return false;
    }

    private boolean matchAccesoMovReducida(@NonNull Instalacion instalacion) {
        return instalacion.getAccesoMovReducida();
    }

    private boolean matchIluminacion(@NonNull Instalacion instalacion) {
        for (int idPista :
                instalacion.getPistas()) {
            if (PistaRepository.getRepository().getPista(idPista).getIluminacion()) {
                return true;
            }
        }
        return false;
    }

}
