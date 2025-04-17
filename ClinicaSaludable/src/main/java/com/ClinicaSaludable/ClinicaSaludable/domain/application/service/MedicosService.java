package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;

import java.sql.SQLDataException;
import java.util.LinkedHashMap;

public interface MedicosService {
    MedicosListRows getListMedicos(Medico medico);
    Boolean InsertMedico(Medico medico);
    Boolean UpdateMedico(Integer id_medico,Medico medico);
    Medico getMedicoById(Integer id_medico);
}
