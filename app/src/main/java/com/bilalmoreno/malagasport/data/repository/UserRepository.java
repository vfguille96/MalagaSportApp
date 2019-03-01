package com.bilalmoreno.malagasport.data.repository;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.data.db.dao.UserDao;
import com.bilalmoreno.malagasport.data.db.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserRepository {

    private static UserRepository repository;

    private User currentUser;
    private UserDao dao;

    private UserRepository() {
        currentUser = new User("NoUser");
        dao = new UserDao();
    }

    public static UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean validarCredenciales(String email, String password) {
        if (dao.validateCredentials(email, password)) {
            this.currentUser = dao.getUser(email);
            MalagaSportApplication.setUserId(email);
            return true;
        }
        return false;
    }

    public boolean registrarUsuario(String id, String email, String nombre, Date fechaNacimiento, String password) {
        User newUser = new User(id.toLowerCase(), email, nombre, fechaNacimiento);
        if (dao.add(newUser, password)) {
            this.currentUser = newUser;
            return true;
        }
        return false;
    }

    public boolean changePassword(String email, String nuevaPassword) {
        return dao.changePassword(email, nuevaPassword);
    }

    public User getUser(String userId) {
        return dao.getUser(userId);
    }
}
