package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.PacientesRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Paciente;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.PacientesListRows;

import java.time.LocalDateTime;
import java.util.*;

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
            Date fecha_nacimiento = element.get("FECHA_NACIMIENTO") != null ? (Date) element.get("FECHA_NACIMIENTO") : null;
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
    public Object InsertPaciente(Paciente paciente) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(paciente);
        pacientesRepository.InsertPaciente(hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Object UpdatePaciente(Integer id_paciente, Paciente paciente) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(paciente);
        pacientesRepository.UpdatePaciente(id_paciente,hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Paciente getPacienteById(Integer id_paciente) {
        LinkedHashMap<String,Object> hmpUser = pacientesRepository.getPacienteById(id_paciente);
        Integer usuario_fk = hmpUser.get("USUARIO_FK") != null ? Integer.parseInt(hmpUser.get("USUARIO_FK").toString()) : null;
        Date fecha_nacimiento = hmpUser.get("FECHA_NACIMIENTO") != null ? (Date) hmpUser.get("FECHA_NACIMIENTO") : null;
        String direccion = hmpUser.get("DIRECCION") != null ? hmpUser.get("DIRECCION").toString() : null;
        String telefono_contacto = hmpUser.get("TELEFONO_CONTACTO") != null ? hmpUser.get("TELEFONO_CONTACTO").toString() : null;
        LocalDateTime fecha_insercion =  hmpUser.get("FECHA_INSERCION") != null ? (LocalDateTime) hmpUser.get("FECHA_INSERCION") : null;
        String usuario_insercion =  hmpUser.get("USUARIO_INSERCION") != null ? (String) hmpUser.get("USUARIO_INSERCION") : null;
        LocalDateTime fecha_modificacion =  hmpUser.get("FECHA_MODIFICACION") != null ? (LocalDateTime) hmpUser.get("FECHA_MODIFICACION") : null;
        String usuario_modificacion = hmpUser.get("USUARIO_MODIFICACION") != null ? (String) hmpUser.get("USUARIO_MODIFICACION") : null;

        return new Paciente(
                id_paciente,
                usuario_fk,
                fecha_nacimiento,
                direccion,
                telefono_contacto,
                fecha_insercion,
                usuario_insercion,
                fecha_modificacion,
                usuario_modificacion
        );
    }
}
