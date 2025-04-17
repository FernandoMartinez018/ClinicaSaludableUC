package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class IngresoAltasRepositoryImpl implements IngresoAltasRepository {
    private final QueryManager connection;
    public IngresoAltasRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> IngresoAltasList(LinkedHashMap<String, Object> hmpParameters) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> InsertIngresoAltas(LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateIngresoAltas(Integer id_medico, LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> getIngresoAltasById(Integer id_ingreso_altas) {
        return null;
    }
}
