package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface HistoriaClinicaRepository {
    LinkedList<LinkedHashMap<String,Object>> HistoriaClinicaList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertHistoriaClinica(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdateHistoriaClinica(Integer id_medico,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getHistoriaClinicaById(Integer id_historia_clinica);
}
