package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Usuario;

import java.util.ArrayList;
import java.util.Calendar;

public class UsuarioRepository {

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private ArrayList<String> passwords;
    private static UsuarioRepository repository;

    private UsuarioRepository() {
        usuario = new Usuario("NoUser");
        usuarios = new ArrayList<>();
        passwords = new ArrayList<>();
        usuarios.add(new Usuario("bilalmoreno92@gmail.com", "bilalmoreno92@gmail.com", "Bilal", Calendar.getInstance()));
        passwords.add("123456");
        usuarios.add(new Usuario("fjgomezflorido@gmail.com", "fjgomezflorido@gmail.com", "Construk", Calendar.getInstance()));
        passwords.add("012345");
        usuarios.add(new Usuario("hola@gmail.com", "hola@gmail.com", "Saludo", Calendar.getInstance()));
        passwords.add("hola12");
    }

    static {
        repository = new UsuarioRepository();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static UsuarioRepository getRepository() {
        return repository;
    }

    public boolean validarCredenciales(String email, String password) {
        if (usuarios.contains(new Usuario(email.toLowerCase())) && passwords.get(usuarios.indexOf(new Usuario(email.toLowerCase()))).equals(password)) {
            this.usuario = usuarios.get(usuarios.indexOf(new Usuario(email.toLowerCase())));
            return true;
        }
        return false;
    }

    public boolean registrarUsuario(String id, String email, String nombre, Calendar fechaNacimiento, String password) {
        Usuario nuevoUsuario = new Usuario(id.toLowerCase(), email, nombre, fechaNacimiento);
        if (!usuarios.contains(nuevoUsuario)) {
            this.usuario = nuevoUsuario;
            usuarios.add(this.usuario);
            passwords.add(password);
            return true;
        }
        return false;
    }

    public boolean cambiarPassword(String email, String nuevaPassword) {
        Usuario usuario = new Usuario(email.toLowerCase());
        if (usuarios.contains(usuario)) {
            passwords.remove(usuarios.indexOf(usuario));
            passwords.add(usuarios.indexOf(usuario), nuevaPassword);
            return true;
        }
        return false;
    }

    public Usuario getUsuario(String idUsuario) {
        for (Usuario usuario :
                usuarios) {
            if (usuario.getId().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }
}
