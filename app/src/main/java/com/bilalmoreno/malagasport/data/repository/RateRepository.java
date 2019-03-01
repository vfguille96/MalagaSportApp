package com.bilalmoreno.malagasport.data.repository;

import com.bilalmoreno.malagasport.data.db.dao.RateDao;
import com.bilalmoreno.malagasport.data.db.model.Rate;

import java.util.ArrayList;

public class RateRepository {
    private static RateRepository repository;


    private RateDao dao;

    private RateRepository() {
        dao = new RateDao();
    }

    public static RateRepository getInstance() {
        if (repository == null) {
            repository = new RateRepository();
        }
        return repository;
    }

    public ArrayList<Rate> getRates(int idInstalacion) {
        return dao.getList(idInstalacion);
    }

    public boolean add(Rate rate) {
        return dao.add(rate);
    }

    public Rate getRate(String idUsuario, int idInstalacion) {
        return dao.get(idUsuario, idInstalacion);
    }

    public boolean edit(Rate rate) {
        return dao.edit(rate);
    }

    public boolean userHasRated(int installationId) {
        return dao.hasUserRated(UserRepository.getInstance().getCurrentUser().getId(), installationId);
    }

    public boolean delete(Rate rate) {
        return dao.delete(rate);
    }
}