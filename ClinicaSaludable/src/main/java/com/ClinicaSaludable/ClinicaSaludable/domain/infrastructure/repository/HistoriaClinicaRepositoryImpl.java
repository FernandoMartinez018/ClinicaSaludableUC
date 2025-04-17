package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class HistoriaClinicaRepositoryImpl implements HistoriaClinicaRepository {
    private final QueryManager connection;
    public HistoriaClinicaRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> HistoriaClinicaList(LinkedHashMap<String, Object> hmpParameters) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> InsertHistoriaClinica(LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateHistoriaClinica(Integer id_medico, LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> getHistoriaClinicaById(Integer id_historia_clinica) {
        return null;
    }
}
