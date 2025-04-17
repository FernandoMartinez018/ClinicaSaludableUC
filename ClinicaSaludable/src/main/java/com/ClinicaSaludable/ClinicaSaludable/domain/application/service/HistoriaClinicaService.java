package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinicaListRows;

public interface HistoriaClinicaService {
    HistoriaClinicaListRows getListHistoriaClinicas(HistoriaClinica historiaClinica);
    Boolean InsertHistoriaClinica(HistoriaClinica historiaClinica);
    Boolean UpdateHistoriaClinica(Integer id_historia_clinica,HistoriaClinica historiaClinica);
    HistoriaClinica getHistoriaClinicaById(Integer id_historia_clinica);
}
