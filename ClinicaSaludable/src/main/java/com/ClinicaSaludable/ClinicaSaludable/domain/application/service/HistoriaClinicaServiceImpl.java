package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.HistoriaClinicaRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinicaListRows;

public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {
    private final HistoriaClinicaRepository historiaClinicaRepository;
    public HistoriaClinicaServiceImpl(HistoriaClinicaRepository historiaClinicaRepository) {this.historiaClinicaRepository = historiaClinicaRepository;}

    @Override
    public HistoriaClinicaListRows getListHistoriaClinicas(HistoriaClinica historiaClinica) {
        return null;
    }

    @Override
    public Boolean InsertHistoriaClinica(HistoriaClinica historiaClinica) {
        return null;
    }

    @Override
    public Boolean UpdateHistoriaClinica(Integer id_historia_clinica, HistoriaClinica historiaClinica) {
        return null;
    }

    @Override
    public HistoriaClinica getHistoriaClinicaById(Integer id_historia_clinica) {
        return null;
    }
}
