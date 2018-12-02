package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Instalacion;

import java.util.ArrayList;

public class InstalacionRepository {
    private ArrayList<Instalacion> instalaciones;
    private static InstalacionRepository repository;

    private InstalacionRepository() {
        instalaciones = new ArrayList<>();
        inicializar();
    }

    public static InstalacionRepository getRepository() {
        return repository;
    }

    public ArrayList<Instalacion> getInstalaciones() {
        ArrayList<Instalacion> resultado = new ArrayList<>();
        for (Instalacion instalacion :
                instalaciones) {
            if (instalacion.getPistas().size() > 0) {
                resultado.add(instalacion);
            }
        }
        return resultado;
    }

    public ArrayList<Instalacion> getWorkout() {
        ArrayList<Instalacion> resultado = new ArrayList<>();
        for (Instalacion instalacion :
                instalaciones) {
            if (instalacion.getMaquinas().size() > 0) {
                resultado.add(instalacion);
            }
        }
        return resultado;
    }

    private void inicializar() {
        //TODO Inicializar InstalacionRepository
        Instalacion instalacion = new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true);
        instalacion.addMaquina(1);
        instalacion.addMaquina(2);
        instalacion.addMaquina(3);

        add(instalacion);

        instalacion = new Instalacion(2, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, false);
        instalacion.addPista(1);
        instalacion.addPista(2);
        add(instalacion);
        add(new Instalacion(3, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(4, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(5, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(6, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(7, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
        add(new Instalacion(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true));
    }

    static {
        repository = new InstalacionRepository();
    }

    private void add(Instalacion instalacion) {
        if (!instalaciones.contains(instalacion)) {
            instalaciones.add(instalacion);
        }
    }

    public Instalacion getInstalacion(int idInstalacion) {
        for (Instalacion instalacion :
                instalaciones) {
            if (instalacion.equals(new Instalacion(idInstalacion))) {
                return instalacion;
            }
        }
        return null;
    }
}
