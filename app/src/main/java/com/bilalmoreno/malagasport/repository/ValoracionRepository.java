package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.MalagaSportApplication;
import com.bilalmoreno.malagasport.pojo.Valoracion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class ValoracionRepository {
    private ArrayList<Valoracion> valoraciones;
    private static ValoracionRepository repository;

    private ValoracionRepository() {
        valoraciones = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        try {
            add(new Valoracion(1, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 1, "Esta todo reventado, el ayuntamiento debería cuidarlo mas!! :("));
            add(new Valoracion(1, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 3, "Instalaciones en condiciones deficientes, aunque el parque está bien :D"));
            add(new Valoracion(2, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 4, "Buenas instalaciones y muy variadas, falta alguna fuente, pero por lo demas correcto."));
            add(new Valoracion(2, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 4, "Todo perfecto."));
            add(new Valoracion(4, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 5, "La mejor instalación que te puedes encontrar."));
            add(new Valoracion(4, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 5, "Instalaciones muy buenas y variadas."));
            add(new Valoracion(3, "fjgomezflorido@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 2, "Instalaciones desgastadas pero hay variedad."));
            add(new Valoracion(3, "bilalmoreno92@gmail.com", MalagaSportApplication.DATE_FORMAT.parse("01/08/2018"), 3, "Instalaciones en condiciones deficientes, pero hay de todo."));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    static {
        repository = new ValoracionRepository();
    }

    public ArrayList<Valoracion> getValoraciones(int idInstalacion) {
        ArrayList<Valoracion> resultado = new ArrayList<>();
        for (Valoracion valoracion :
                valoraciones) {
            if (valoracion.getIdInstlacion() == idInstalacion) {
                resultado.add(valoracion);
            }
        }
        return resultado;
    }

    public static ValoracionRepository getRepository() {
        return repository;
    }

    public void add(Valoracion valoracion) {
        valoraciones.add(valoracion);
    }

    public Valoracion getValoracion(String idUsuario, int idInstalacion) {
        for (Valoracion valoracion :
                valoraciones) {
            if (valoracion.getIdUsuario().equals(idUsuario) && valoracion.getIdInstlacion() == idInstalacion) {
                return valoracion;
            }
        }
        return null;
    }

    public void edit(Valoracion valoracion) {
        for (int i = 0; i < valoraciones.size(); i++) {
            if (valoracion.equals(valoraciones.get(i))) {
                valoracion.setFechaEdicion(Calendar.getInstance().getTime());
                valoraciones.remove(i);
                valoraciones.add(i, valoracion);
            }
        }
//        int index = valoraciones.indexOf(valoracion);
//        valoraciones.remove(index);
//        valoraciones.add(index, valoracion);
    }
}