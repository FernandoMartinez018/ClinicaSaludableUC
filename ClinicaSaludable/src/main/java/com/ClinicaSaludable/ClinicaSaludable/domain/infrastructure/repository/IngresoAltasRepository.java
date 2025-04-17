package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface IngresoAltasRepository {
    LinkedList<LinkedHashMap<String,Object>> IngresoAltasList(LinkedHashMap<String,Object> hmpParameters);
    LinkedHashMap<String,Object> InsertIngresoAltas(LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> UpdateIngresoAltas(Integer id_medico,LinkedHashMap<String,Object> hmpEntitie);
    LinkedHashMap<String,Object> getIngresoAltasById(Integer id_ingreso_altas);
}
