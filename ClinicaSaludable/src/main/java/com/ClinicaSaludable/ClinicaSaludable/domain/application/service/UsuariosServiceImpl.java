package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.UsuariosRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Usuario;

public class UsuariosServiceImpl implements UsuariosService{
    private final UsuariosRepository usuariosRepository;
    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {this.usuariosRepository = usuariosRepository;}

    @Override
    public Boolean InsertUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Boolean UpdateUsuario(Integer id_usuario, Usuario usuario) {
        return null;
    }

    @Override
    public Usuario getUsuarioById(Integer id_usuario) {
        return null;
    }
}
