package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.CamasRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Cama;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CamasListRows;

public class CamasServiceImpl implements CamasService {
    private CamasRepository camasRepository;
    public CamasServiceImpl(CamasRepository camasRepository) {this.camasRepository = camasRepository;}

    @Override
    public CamasListRows getListCamas(Cama cama) {
        return null;
    }

    @Override
    public Boolean InsertCama(Cama cama) {
        return null;
    }

    @Override
    public Boolean UpdateCama(Integer id_cama, Cama cama) {
        return null;
    }

    @Override
    public Cama getCamaById(Integer id_cama) {
        return null;
    }
}
