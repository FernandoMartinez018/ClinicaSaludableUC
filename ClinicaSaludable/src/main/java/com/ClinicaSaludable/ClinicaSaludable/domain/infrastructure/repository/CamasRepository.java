package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface CamasRepository {
    LinkedList<LinkedHashMap<String,Object>> CamasList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertCama(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdateCama(Integer id_medico,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getCamaById(Integer id_cama);
}
