package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.Cama;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CamasListRows;

public interface CamasService {
    CamasListRows getListCamas(Cama cama);
    Object InsertCama(Cama cama);
    Object UpdateCama(Integer id_cama,Cama cama);
    Cama getCamaById(Integer id_cama);
}
