package com.bilalmoreno.malagasport.data.db.repository;

import com.bilalmoreno.MalagaSportApplication;
import com.bilalmoreno.malagasport.data.db.model.Valoration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class ValorationRepository {
    private static ValorationRepository repository;

    static {
        repository = new ValorationRepository();
    }

    private ArrayList<Valoration> valoraciones;

    private ValorationRepository() {
        valoraciones = new ArrayList<>();
        inicializar();
    }

    public static ValorationRepository getRepository() {
        return repository;
    }

    private void inicializar() {
        try {
            add(new Valoration(1, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 1, "Esta todo reventado, el ayuntamiento debería cuidarlo mas!! :("));
            add(new Valoration(1, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 3, "Instalaciones en condiciones deficientes, aunque el parque está bien :D"));
            add(new Valoration(2, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 4, "Buenas instalaciones y muy variadas, falta alguna fuente, pero por lo demas correcto."));
            add(new Valoration(2, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 4, "Todo perfecto."));
            add(new Valoration(4, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 5, "La mejor instalación que te puedes encontrar."));
            add(new Valoration(4, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 5, "Instalaciones muy buenas y variadas."));
            add(new Valoration(3, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 2, "Instalaciones desgastadas pero hay variedad."));
            add(new Valoration(3, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 3, "Instalaciones en condiciones deficientes, pero hay de todo."));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Valoration> getValoraciones(int idInstalacion) {
        ArrayList<Valoration> resultado = new ArrayList<>();
        for (Valoration valoration :
                valoraciones) {
            if (valoration.getIdInstlacion() == idInstalacion) {
                resultado.add(valoration);
            }
        }
        return resultado;
    }

    public void add(Valoration valoration) {
        valoraciones.add(valoration);
    }

    public Valoration getValoracion(String idUsuario, int idInstalacion) {
        for (Valoration valoration :
                valoraciones) {
            if (valoration.getIdUsuario().equals(idUsuario) && valoration.getIdInstlacion() == idInstalacion) {
                return valoration;
            }
        }
        return null;
    }

    public void edit(Valoration valoration) {
        for (int i = 0; i < valoraciones.size(); i++) {
            if (valoration.equals(valoraciones.get(i))) {
                valoration.setFechaEdicion(Calendar.getInstance().getTime());
                valoraciones.remove(i);
                valoraciones.add(i, valoration);
            }
        }
//        int index = valoraciones.indexOf(valoration);
//        valoraciones.remove(index);
//        valoraciones.add(index, valoration);
    }

    public boolean userHasRated(int installationId) {
        for (int i = 0; i < valoraciones.size(); i++) {
            if (installationId == valoraciones.get(i).getIdInstlacion()) {
                return true;
            }
        }
        return false;
    }
}