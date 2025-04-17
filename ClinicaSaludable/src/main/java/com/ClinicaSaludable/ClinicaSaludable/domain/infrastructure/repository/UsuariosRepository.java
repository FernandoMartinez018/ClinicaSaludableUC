package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface UsuariosRepository {
    LinkedList<LinkedHashMap<String,Object>> UsuariosList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertUsuarios(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdateUsuarios(Integer id_medico,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getUsuariosById(Integer id_usuario);
}
