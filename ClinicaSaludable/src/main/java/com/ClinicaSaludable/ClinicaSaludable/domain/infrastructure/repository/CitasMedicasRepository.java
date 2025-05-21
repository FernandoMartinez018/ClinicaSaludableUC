package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface CitasMedicasRepository {
    LinkedList<LinkedHashMap<String,Object>> CitasMedicasList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertCitasMedica(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdateCitasMedica(Integer id_cita_medica,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getCitaMedicaById(Integer id_cita_medica);
}
