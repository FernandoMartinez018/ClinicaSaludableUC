package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class UsuariosRepositoryImpl implements UsuariosRepository {
    private final QueryManager connection;
    public UsuariosRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> UsuariosList(LinkedHashMap<String, Object> hmpParameters) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> InsertUsuarios(LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateUsuarios(Integer id_medico, LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> getUsuariosById(Integer id_usuario) {
        return null;
    }
}
