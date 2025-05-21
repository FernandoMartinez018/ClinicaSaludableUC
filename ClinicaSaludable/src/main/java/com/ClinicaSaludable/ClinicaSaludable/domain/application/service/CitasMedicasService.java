package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedicaListRows;

public interface CitasMedicasService {
    CitaMedicaListRows getListCitasMedicas(CitaMedica citaMedica);
    Object InsertCitaMedica(CitaMedica citaMedica);
    Object UpdateCitaMedica(Integer id_cita_medica,CitaMedica citaMedica);
    CitaMedica getCitaMedicaById(Integer id_cita_medica);
}
