package com.bilalmoreno.malagasport.data.repository;

import com.bilalmoreno.malagasport.data.db.dao.MachineDao;
import com.bilalmoreno.malagasport.data.db.model.Machine;

import java.util.ArrayList;

public class MachineRepository {
    private static MachineRepository repository;

    private MachineDao dao;

    private MachineRepository() {
        dao = new MachineDao();
    }

    public static MachineRepository getInstance() {
        if (repository == null) {
            repository = new MachineRepository();
        }
        return repository;
    }

    public ArrayList<Machine> getAll() {
        return dao.getAll();
    }

    public ArrayList<Machine> getList(int workoutId) {
        return dao.getList(workoutId);
    }

    public boolean add(Machine machine) {
        return dao.add(machine);
    }

    public Machine get(int machineId) {
        return dao.get(machineId);
    }

    public boolean update(Machine machine) {
        return dao.update(machine);
    }

    public boolean update(ArrayList<Machine> machines) {
        return dao.update(machines);
    }
}
