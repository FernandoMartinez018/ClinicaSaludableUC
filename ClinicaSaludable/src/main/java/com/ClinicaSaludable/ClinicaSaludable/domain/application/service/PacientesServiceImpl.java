package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.PacientesRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Paciente;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.PacientesListRows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class PacientesServiceImpl implements PacientesService {
    private final PacientesRepository pacientesRepository;
    public PacientesServiceImpl(PacientesRepository pacientesRepository) {this.pacientesRepository = pacientesRepository;}

    @Override
    public PacientesListRows getListPacientes(Paciente paciente) {
        LinkedList<LinkedHashMap<String,Object>> lstPacientes = pacientesRepository.PacientesList(Utils.convertRecordToLinkedHash(paciente));
        List<Paciente> Paciente = new ArrayList<>();
        for (LinkedHashMap<String,Object> element : lstPacientes) {
            Integer id_paciente =  element.get("ID_PACIENTE") != null ? Integer.parseInt(element.get("ID_PACIENTE").toString()) : null;
            Integer usuario_fk = element.get("USUARIO_FK") != null ? Integer.parseInt(element.get("USUARIO_FK").toString()) : null;
            LocalDateTime fecha_nacimiento = element.get("FECHA_NACIMIENTO") != null ? LocalDateTime.parse(element.get("FECHA_NACIMIENTO").toString()) : null;
            String direccion = element.get("DIRECCION") != null ? element.get("DIRECCION").toString() : null;
            String telefono_contacto = element.get("TELEFONO_CONTACTO") != null ? element.get("TELEFONO_CONTACTO").toString() : null;
            LocalDateTime fecha_insercion =  element.get("FECHA_INSERCION") != null ? (LocalDateTime) element.get("FECHA_INSERCION") : null;
            String usuario_insercion =  element.get("USUARIO_INSERCION") != null ? (String) element.get("USUARIO_INSERCION") : null;
            LocalDateTime fecha_modificacion =  element.get("FECHA_MODIFICACION") != null ? (LocalDateTime) element.get("FECHA_MODIFICACION") : null;
            String usuario_modificacion = element.get("USUARIO_MODIFICACION") != null ? (String) element.get("USUARIO_MODIFICACION") : null;
            Paciente.add(new Paciente(
                    id_paciente,
                    usuario_fk,
                    fecha_nacimiento,
                    direccion,
                    telefono_contacto,
                    fecha_insercion,
                    usuario_insercion,
                    fecha_modificacion,
                    usuario_modificacion
            ));
        }
        return new PacientesListRows(Paciente);
    }

    @Override
    public Boolean InsertPaciente(Paciente paciente) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(paciente);
        pacientesRepository.InsertPaciente(hmpEntitie);
        return true;
    }

    @Override
    public Boolean UpdatePaciente(Integer id_paciente, Paciente paciente) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(paciente);
        pacientesRepository.UpdatePaciente(id_paciente,hmpEntitie);
        return true;
    }

    @Override
    public Paciente getPacienteById(Integer id_paciente) {
        return null;
    }
}
