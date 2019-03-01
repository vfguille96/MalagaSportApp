package com.bilalmoreno.malagasport.data.repository;

import com.bilalmoreno.malagasport.data.db.dao.TrackDao;
import com.bilalmoreno.malagasport.data.db.model.ActividadDeportiva;
import com.bilalmoreno.malagasport.data.db.model.Track;

import java.util.ArrayList;

public class TrackRepository {
    private static TrackRepository repository;

    private TrackDao dao;

    private TrackRepository() {
        dao = new TrackDao();
    }

    public static TrackRepository getInstance() {
        if (repository == null) {
            repository = new TrackRepository();
        }
        return repository;
    }

    private boolean add(Track track, int installationId) {
        return dao.add(track, installationId);
    }

    public ArrayList<Track> getTracks() {
        return dao.getAll();
    }

    public Track get(int trackId) {
        return dao.get(trackId);
    }
}
