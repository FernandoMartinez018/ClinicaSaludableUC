package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Usuario;

import java.security.NoSuchAlgorithmException;

public interface UsuariosService {
   // MedicosListRows getListUsuario(Usuario usuario);
    Object InsertUsuario(Usuario usuario) throws NoSuchAlgorithmException;
    Object UpdateUsuario(Integer id_usuario,Usuario usuario);
    Usuario getUsuarioById(Integer id_usuario);
    Object validarUsuario(String email, String password) throws NoSuchAlgorithmException;
}
