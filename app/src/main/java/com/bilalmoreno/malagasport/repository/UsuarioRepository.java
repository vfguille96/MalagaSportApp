package com.bilalmoreno.malagasport.repository;

import com.bilalmoreno.malagasport.pojo.Usuario;

public class UsuarioRepository {
    private Usuario usuario;
    private static UsuarioRepository repository;

    private UsuarioRepository() {
        usuario = new Usuario("NoUser");
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
}
