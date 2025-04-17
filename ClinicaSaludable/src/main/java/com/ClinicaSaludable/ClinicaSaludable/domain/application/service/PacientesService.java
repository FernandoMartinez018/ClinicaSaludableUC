package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Paciente;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.PacientesListRows;

public interface PacientesService {
    PacientesListRows getListPacientes(Paciente paciente);
    Boolean InsertPaciente(Paciente paciente);
    Boolean UpdatePaciente(Integer id_paciente,Paciente paciente);
    Paciente getPacienteById(Integer id_paciente);
}
