package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.CitasMedicasRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedicaListRows;

public class CitasMedicasServiceImpl implements CitasMedicasService {
    private CitasMedicasRepository citasMedicasRepository;
    public CitasMedicasServiceImpl(CitasMedicasRepository citasMedicasRepository) {this.citasMedicasRepository = citasMedicasRepository;}


    @Override
    public CitaMedicaListRows getListCitasMedicas(CitaMedica citaMedica) {
        return null;
    }

    @Override
    public Boolean InsertCitaMedica(CitaMedica citaMedica) {
        return null;
    }

    @Override
    public Boolean UpdateCitaMedica(Integer id_cita_medica, CitaMedica citaMedica) {
        return null;
    }

    @Override
    public CitaMedica getCitaMedicaById(Integer id_cita_medica) {
        return null;
    }
}
