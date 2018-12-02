package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Maquina;

import java.util.ArrayList;

public class MaquinaRepository {
    private ArrayList<Maquina> maquinas;
    private static MaquinaRepository repository;

    static {
        repository = new MaquinaRepository();
    }

    private MaquinaRepository() {
        maquinas = new ArrayList<>();
        inicializar();
    }

    public static MaquinaRepository getRepository() {
        return repository;
    }

    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    public ArrayList<Maquina> getMaquinas(ArrayList<Integer> idsMaquinas) {
        ArrayList<Maquina> resultado = new ArrayList<>();
        for (int idMaquina :
                idsMaquinas) {
            resultado.add(getMaquina(idMaquina));
        }
        return resultado;
    }

    private void inicializar() {
        //TODO Inicializar MaquinaRepository
        add(new Maquina(1, "Banco", 1, "Sentarse", "Sentadillas arriba y abajo.", "Se pueden cargar los anteebrazos" ));
        add(new Maquina(2, "Banco", 2, "Sentarse", "Sentadillas arriba y abajo.", "Se pueden cargar los anteebrazos" ));
        add(new Maquina(3, "Banco", 4, "Sentarse", "Sentadillas arriba y abajo.", "Se pueden cargar los anteebrazos" ));
    }

    private void add(Maquina maquina) {
        maquinas.add(maquina);
    }

    public Maquina getMaquina(int idMaquina) {
        Maquina maquinaBuscada = new Maquina(idMaquina);
        for (Maquina maquina :
                maquinas) {
            if (maquina.equals(maquinaBuscada)) {
                return maquina;
            }
        }
        return null;
    }
}
