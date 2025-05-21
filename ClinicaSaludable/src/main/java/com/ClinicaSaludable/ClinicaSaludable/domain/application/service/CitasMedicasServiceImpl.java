package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.CitasMedicasRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedicaListRows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CitasMedicasServiceImpl implements CitasMedicasService {
    private CitasMedicasRepository citasMedicasRepository;
    public CitasMedicasServiceImpl(CitasMedicasRepository citasMedicasRepository) {this.citasMedicasRepository = citasMedicasRepository;}


    @Override
    public CitaMedicaListRows getListCitasMedicas(CitaMedica citaMedica) {
        LinkedList<LinkedHashMap<String,Object>> hmpCitasMedicas = citasMedicasRepository.CitasMedicasList(Utils.convertRecordToLinkedHash(citaMedica));
        List<CitaMedica> CitaMedica = new ArrayList<>();
        for(LinkedHashMap<String,Object> element : hmpCitasMedicas){
            Integer id_cita_medica = element.get("ID_CITA_MEDICA") != null ? Integer.parseInt(element.get("ID_CITA_MEDICA").toString()) : null;
            Integer pacientes_fk = element.get("PACIENTES_FK") != null ? Integer.parseInt(element.get("PACIENTES_FK").toString()) : null;
            Integer medico_fk = element.get("MEDICO_FK") != null ? Integer.parseInt(element.get("MEDICO_FK").toString()) : null;
            LocalDateTime fecha_cita = element.get("FECHA_CITA") != null ? LocalDateTime.parse(element.get("FECHA_CITA").toString()) : null;
            String estado_cita = element.get("ESTADO_CITA") != null ? element.get("ESTADO_CITA").toString() : null;
            LocalDateTime fecha_insercion =  element.get("FECHA_INSERCION") != null ? (LocalDateTime) element.get("FECHA_INSERCION") : null;
            String usuario_insercion =  element.get("USUARIO_INSERCION") != null ? (String) element.get("USUARIO_INSERCION") : null;
            LocalDateTime fecha_modificacion =  element.get("FECHA_MODIFICACION") != null ? (LocalDateTime) element.get("FECHA_MODIFICACION") : null;
            String usuario_modificacion = element.get("USUARIO_MODIFICACION") != null ? (String) element.get("USUARIO_MODIFICACION") : null;
        CitaMedica.add(new CitaMedica(
                id_cita_medica,
                pacientes_fk,
                medico_fk,
                fecha_cita,
                estado_cita,
                fecha_insercion,
                usuario_insercion,
                fecha_modificacion,
                usuario_modificacion
        ));
        }
        return new CitaMedicaListRows(CitaMedica);
    }

    @Override
    public Object InsertCitaMedica(CitaMedica citaMedica) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(citaMedica);
        citasMedicasRepository.InsertCitasMedica(hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Object UpdateCitaMedica(Integer id_cita_medica, CitaMedica citaMedica) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(citaMedica);
        citasMedicasRepository.UpdateCitasMedica(id_cita_medica,hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public CitaMedica getCitaMedicaById(Integer id_cita_medica) {
LinkedHashMap<String,Object> hmpCitaMedica = citasMedicasRepository.getCitaMedicaById(id_cita_medica);
        Integer pacientes_fk = hmpCitaMedica.get("PACIENTES_FK") != null ? Integer.parseInt(hmpCitaMedica.get("PACIENTES_FK").toString()) : null;
        Integer medico_fk = hmpCitaMedica.get("MEDICO_FK") != null ? Integer.parseInt(hmpCitaMedica.get("MEDICO_FK").toString()) : null;
        LocalDateTime fecha_cita = hmpCitaMedica.get("FECHA_CITA") != null ? LocalDateTime.parse(hmpCitaMedica.get("FECHA_CITA").toString()) : null;
        String estado_cita = hmpCitaMedica.get("ESTADO_CITA") != null ? hmpCitaMedica.get("ESTADO_CITA").toString() : null;
        LocalDateTime fecha_insercion =  hmpCitaMedica.get("FECHA_INSERCION") != null ? (LocalDateTime) hmpCitaMedica.get("FECHA_INSERCION") : null;
        String usuario_insercion =  hmpCitaMedica.get("USUARIO_INSERCION") != null ? (String) hmpCitaMedica.get("USUARIO_INSERCION") : null;
        LocalDateTime fecha_modificacion =  hmpCitaMedica.get("FECHA_MODIFICACION") != null ? (LocalDateTime) hmpCitaMedica.get("FECHA_MODIFICACION") : null;
        String usuario_modificacion = hmpCitaMedica.get("USUARIO_MODIFICACION") != null ? (String) hmpCitaMedica.get("USUARIO_MODIFICACION") : null;

        return new CitaMedica(
                id_cita_medica,
                pacientes_fk,
                medico_fk,
                fecha_cita,
                estado_cita,
                fecha_insercion,
                usuario_insercion,
                fecha_modificacion,
                usuario_modificacion
        );
    }
}
