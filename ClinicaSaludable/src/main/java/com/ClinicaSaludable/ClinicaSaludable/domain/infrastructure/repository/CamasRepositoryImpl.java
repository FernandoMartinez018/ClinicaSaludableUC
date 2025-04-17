package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CamasRepositoryImpl implements CamasRepository {

    private final QueryManager connection;
    public CamasRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> CamasList(LinkedHashMap<String, Object> hmpParameters) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> InsertCama(LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateCama(Integer id_medico, LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> getCamaById(Integer id_cama) {
        return null;
    }
}
