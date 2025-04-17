package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CitasMedicasRepositoryImpl implements CitasMedicasRepository {
private final QueryManager connetion;
public CitasMedicasRepositoryImpl(QueryManager queryManager) {this.connetion = queryManager;}
    @Override
    public LinkedList<LinkedHashMap<String, Object>> CitasMedicasList(LinkedHashMap<String, Object> hmpParameters) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> InsertCitasMedica(LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateCitasMedica(Integer id_medico, LinkedHashMap<String, Object> hmpEntitie) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> getCitaMedicaById(Integer id_cita_medica) {
        return null;
    }
}
