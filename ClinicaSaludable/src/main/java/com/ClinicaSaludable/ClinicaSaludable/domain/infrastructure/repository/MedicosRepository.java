package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;

import java.sql.SQLDataException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface MedicosRepository {
    LinkedList<LinkedHashMap<String,Object>> MedicosList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertMedico(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdateMedico(Integer id_medico,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getMedicoById(Integer id_medico);
}
