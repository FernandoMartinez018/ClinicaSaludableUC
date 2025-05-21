package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.HistoriaClinicaRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinicaListRows;

import java.time.LocalDateTime;
import java.util.*;

public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {
    private final HistoriaClinicaRepository historiaClinicaRepository;
    public HistoriaClinicaServiceImpl(HistoriaClinicaRepository historiaClinicaRepository) {this.historiaClinicaRepository = historiaClinicaRepository;}

    @Override
    public HistoriaClinicaListRows getListHistoriaClinicas(HistoriaClinica historiaClinica) {
        LinkedList<LinkedHashMap<String,Object>> hmpHistoriaClinicas = historiaClinicaRepository.HistoriaClinicaList(Utils.convertRecordToLinkedHash(historiaClinica));
        List<HistoriaClinica> HistoriaClinica = new ArrayList<>();
        for(LinkedHashMap<String,Object> element : hmpHistoriaClinicas){
            Integer id_historia_clinica = element.get("ID_HISTORIA_CLINICA") != null ? Integer.parseInt(element.get("ID_HISTORIA_CLINICA").toString()) : null;
            Integer pacientes_fk = element.get("PACIENTES_FK") != null ? Integer.parseInt(element.get("PACIENTES_FK").toString()) : null;
            Integer medico_fk = element.get("MEDICO_FK") != null ? Integer.parseInt(element.get("MEDICO_FK").toString()) : null;
            Date fecha_historia = element.get("FECHA_HISTORIA") != null ? (Date) element.get("FECHA_HISTORIA") : null;
            String diagnostico = element.get("DIAGNOSTICO") != null ? (String) element.get("DIAGNOSTICO") : null;
            String tratamiento = element.get("TRATAMIENTO") != null ? (String) element.get("TRATAMIENTO") : null;
            LocalDateTime fecha_insercion =  element.get("FECHA_INSERCION") != null ? (LocalDateTime) element.get("FECHA_INSERCION") : null;
            String usuario_insercion =  element.get("USUARIO_INSERCION") != null ? (String) element.get("USUARIO_INSERCION") : null;
            LocalDateTime fecha_modificacion =  element.get("FECHA_MODIFICACION") != null ? (LocalDateTime) element.get("FECHA_MODIFICACION") : null;
            String usuario_modificacion = element.get("USUARIO_MODIFICACION") != null ? (String) element.get("USUARIO_MODIFICACION") : null;
            HistoriaClinica.add(new HistoriaClinica(
                    id_historia_clinica,
                    pacientes_fk,
                    medico_fk,
                    fecha_historia,
                    diagnostico,
                    tratamiento,
                    fecha_insercion,
                    usuario_insercion,
                    fecha_modificacion,
                    usuario_modificacion
            ));
        }
        return new HistoriaClinicaListRows(HistoriaClinica);
    }

    @Override
    public Object InsertHistoriaClinica(HistoriaClinica historiaClinica) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(historiaClinica);
        historiaClinicaRepository.InsertHistoriaClinica(hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Object UpdateHistoriaClinica(Integer id_historia_clinica, HistoriaClinica historiaClinica) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(historiaClinica);
        historiaClinicaRepository.UpdateHistoriaClinica(id_historia_clinica,hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public HistoriaClinica getHistoriaClinicaById(Integer id_historia_clinica) {
        LinkedHashMap<String,Object> hmpHistoriaClinica = historiaClinicaRepository.getHistoriaClinicaById(id_historia_clinica);
        Integer pacientes_fk = hmpHistoriaClinica.get("PACIENTES_FK") != null ? Integer.parseInt(hmpHistoriaClinica.get("PACIENTES_FK").toString()) : null;
        Integer medico_fk = hmpHistoriaClinica.get("MEDICO_FK") != null ? Integer.parseInt(hmpHistoriaClinica.get("MEDICO_FK").toString()) : null;
        Date fecha_historia = hmpHistoriaClinica.get("FECHA_HISTORIA") != null ? (Date) hmpHistoriaClinica.get("FECHA_HISTORIA") : null;
        String diagnostico = hmpHistoriaClinica.get("DIAGNOSTICO") != null ? (String) hmpHistoriaClinica.get("DIAGNOSTICO") : null;
        String tratamiento = hmpHistoriaClinica.get("TRATAMIENTO") != null ? (String) hmpHistoriaClinica.get("TRATAMIENTO") : null;
        LocalDateTime fecha_insercion =  hmpHistoriaClinica.get("FECHA_INSERCION") != null ? (LocalDateTime) hmpHistoriaClinica.get("FECHA_INSERCION") : null;
        String usuario_insercion =  hmpHistoriaClinica.get("USUARIO_INSERCION") != null ? (String) hmpHistoriaClinica.get("USUARIO_INSERCION") : null;
        LocalDateTime fecha_modificacion =  hmpHistoriaClinica.get("FECHA_MODIFICACION") != null ? (LocalDateTime) hmpHistoriaClinica.get("FECHA_MODIFICACION") : null;
        String usuario_modificacion = hmpHistoriaClinica.get("USUARIO_MODIFICACION") != null ? (String) hmpHistoriaClinica.get("USUARIO_MODIFICACION") : null;
    return new HistoriaClinica(
            id_historia_clinica,
            pacientes_fk,
            medico_fk,
            fecha_historia,
            diagnostico,
            tratamiento,
            fecha_insercion,
            usuario_insercion,
            fecha_modificacion,
            usuario_modificacion
    );
    }
}
