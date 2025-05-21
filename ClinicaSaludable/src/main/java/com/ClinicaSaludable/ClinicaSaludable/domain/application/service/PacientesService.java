package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Paciente;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.PacientesListRows;

public interface PacientesService {
    PacientesListRows getListPacientes(Paciente paciente);
    Object InsertPaciente(Paciente paciente);
    Object UpdatePaciente(Integer id_paciente,Paciente paciente);
    Paciente getPacienteById(Integer id_paciente);
}
