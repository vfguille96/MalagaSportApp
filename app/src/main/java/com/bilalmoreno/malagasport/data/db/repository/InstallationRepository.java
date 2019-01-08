package com.bilalmoreno.malagasport.data.db.repository;

import com.bilalmoreno.malagasport.data.db.model.Installation;

import java.util.ArrayList;

public class InstallationRepository {
    private static InstallationRepository repository;

    static {
        repository = new InstallationRepository();
    }

    private ArrayList<Installation> instalaciones;

    private InstallationRepository() {
        instalaciones = new ArrayList<>();
        inicializar();
    }

    public static InstallationRepository getRepository() {
        return repository;
    }

    public ArrayList<Installation> getInstalaciones() {
        ArrayList<Installation> resultado = new ArrayList<>();
        for (Installation installation :
                instalaciones) {
            if (installation.getPistas().size() > 0) {
                resultado.add(installation);
            }
        }
        return resultado;
    }

    public ArrayList<Installation> getWorkout() {
        ArrayList<Installation> resultado = new ArrayList<>();
        for (Installation installation :
                instalaciones) {
            if (installation.getMaquinas().size() > 0) {
                resultado.add(installation);
            }
        }
        return resultado;
    }

    private void inicializar() {
        //TODO Inicializar InstallationRepository
        Installation installation = new Installation(1, "Parque del Oeste", "Calle Realenga de San Luis, 11, 29004 Málaga", 36.692941, -4.446162, false, true);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);

        add(installation);

        installation = new Installation(2, "Parque del Sur", "Calle Zaragoza, 8, 29004 Málaga", 36.792941, -4.446162, false, false);
        installation.addPista(1);
        installation.addPista(2);

        add(installation);

        installation = new Installation(3, "Parque del Norte", "Plaza de Ceuta, 13, 29004 Málaga", 36.692941, -4.446162, false, true);
        installation.addMaquina(9);
        installation.addMaquina(8);
        installation.addMaquina(10);
        installation.addPista(1);
        installation.addPista(5);
        installation.addPista(3);
        installation.addPista(4);

        add(installation);

        installation = new Installation(4, "Carranque", "Av. Rosa de Lima, 8, 29004 Málaga", 36.7167364, -4.4455053, true, true);
        installation.addPista(1);
        installation.addPista(2);
        installation.addPista(3);
        installation.addPista(4);
        installation.addPista(5);
        installation.addPista(6);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);
        installation.addMaquina(4);
        installation.addMaquina(5);
        installation.addMaquina(6);
        installation.addMaquina(7);
        installation.addMaquina(8);
        installation.addMaquina(9);
        installation.addMaquina(10);


        add(installation);
        installation = new Installation(5, "Carranque", "Av. Rosa de Lima, 8, 29004 Málaga", 36.7167364, -4.4455053, true, true);
        installation.addPista(1);
        installation.addPista(2);
        installation.addPista(3);
        installation.addPista(4);
        installation.addPista(5);
        installation.addPista(6);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);
        installation.addMaquina(4);
        installation.addMaquina(5);
        installation.addMaquina(6);
        installation.addMaquina(7);
        installation.addMaquina(8);
        installation.addMaquina(9);
        installation.addMaquina(10);


        add(installation);
        installation = new Installation(6, "Carranque", "Av. Rosa de Lima, 8, 29004 Málaga", 36.7167364, -4.4455053, true, true);
        installation.addPista(1);
        installation.addPista(2);
        installation.addPista(3);
        installation.addPista(4);
        installation.addPista(5);
        installation.addPista(6);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);
        installation.addMaquina(4);
        installation.addMaquina(5);
        installation.addMaquina(6);
        installation.addMaquina(7);
        installation.addMaquina(8);
        installation.addMaquina(9);
        installation.addMaquina(10);


        add(installation);
        installation = new Installation(7, "Carranque", "Av. Rosa de Lima, 8, 29004 Málaga", 36.7167364, -4.4455053, true, true);
        installation.addPista(1);
        installation.addPista(2);
        installation.addPista(3);
        installation.addPista(4);
        installation.addPista(5);
        installation.addPista(6);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);
        installation.addMaquina(4);
        installation.addMaquina(5);
        installation.addMaquina(6);
        installation.addMaquina(7);
        installation.addMaquina(8);
        installation.addMaquina(9);
        installation.addMaquina(10);


        add(installation);
        installation = new Installation(8, "Carranque", "Av. Rosa de Lima, 8, 29004 Málaga", 36.7167364, -4.4455053, true, true);
        installation.addPista(1);
        installation.addPista(2);
        installation.addPista(3);
        installation.addPista(4);
        installation.addPista(5);
        installation.addPista(6);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);
        installation.addMaquina(4);
        installation.addMaquina(5);
        installation.addMaquina(6);
        installation.addMaquina(7);
        installation.addMaquina(8);
        installation.addMaquina(9);
        installation.addMaquina(10);


        add(installation);
        installation = new Installation(9, "Carranque", "Av. Rosa de Lima, 8, 29004 Málaga", 36.7167364, -4.4455053, true, true);
        installation.addPista(1);
        installation.addPista(2);
        installation.addPista(3);
        installation.addPista(4);
        installation.addPista(5);
        installation.addPista(6);
        installation.addMaquina(1);
        installation.addMaquina(2);
        installation.addMaquina(3);
        installation.addMaquina(4);
        installation.addMaquina(5);
        installation.addMaquina(6);
        installation.addMaquina(7);
        installation.addMaquina(8);
        installation.addMaquina(9);
        installation.addMaquina(10);


        add(installation);
    }

    private void add(Installation installation) {
        if (!instalaciones.contains(installation)) {
            instalaciones.add(installation);
        }
    }

    public Installation getInstalacion(int idInstalacion) {
        for (Installation installation :
                instalaciones) {
            if (installation.equals(new Installation(idInstalacion))) {
                return installation;
            }
        }
        return null;
    }
}
