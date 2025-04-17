package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Usuario;

public interface UsuariosService {
   // MedicosListRows getListUsuario(Usuario usuario);
    Boolean InsertUsuario(Usuario usuario);
    Boolean UpdateUsuario(Integer id_usuario,Usuario usuario);
    Usuario getUsuarioById(Integer id_usuario);
}
