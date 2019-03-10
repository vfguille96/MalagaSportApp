package com.bilalmoreno.malagasport.data.repository;

import com.bilalmoreno.malagasport.data.db.dao.InstallationDao;
import com.bilalmoreno.malagasport.data.db.dao.WorkoutDao;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Workout;

import java.util.ArrayList;

public class InstallationRepository {
    private static InstallationRepository repository;

    private InstallationDao installationDao;
    private WorkoutDao workoutDao;

    private InstallationRepository() {
        installationDao = new InstallationDao();
        workoutDao = new WorkoutDao();
    }

    public static InstallationRepository getInstance() {
        if (repository == null) {
            repository = new InstallationRepository();
        }
        return repository;
    }

    public ArrayList<Installation> getInstallations() {
        return installationDao.getAll();
    }

    public ArrayList<Workout> getWorkouts() {
        return workoutDao.getAll();
    }

    public boolean add(Installation installation) {
        return installationDao.add(installation);
    }

    public boolean add(Workout workout) {
        return workoutDao.add(workout);
    }

    public Installation getInstalacion(int idInstalacion) {
        return installationDao.get(idInstalacion);
    }

    public Workout getWorkout(int workoutId) {
        return workoutDao.get(workoutId);
    }
}
