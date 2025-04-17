package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface PacientesRepository {
    LinkedList<LinkedHashMap<String,Object>> PacientesList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertPaciente(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdatePaciente(Integer id_medico,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getPacienteById(Integer id_paciente);
}
