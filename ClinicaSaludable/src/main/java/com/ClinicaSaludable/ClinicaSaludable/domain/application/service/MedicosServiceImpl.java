package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.MedicosRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.*;

public class MedicosServiceImpl implements MedicosService {
public final MedicosRepository medicosRepository;
MedicosServiceImpl(MedicosRepository medicosRepository) {
this.medicosRepository = medicosRepository;
}

    @Override
    public MedicosListRows getListMedicos(Medico medico) {

        LinkedList<LinkedHashMap<String,Object>> medicos = medicosRepository.MedicosList(Utils.convertRecordToLinkedHash(medico));
        List<Medico> Medico = new ArrayList<>();
        for (LinkedHashMap<String,Object> element : medicos) {
            Integer id_medico = element.get("ID_MEDICO") != null ? (int) element.get("ID_MEDICO") : null;
            Integer usuario_fk =  element.get("USUARIO_FK") != null ? (int) element.get("USUARIO_FK") : null;
            String especialidad = element.get("ESPECIALIDAD") != null ? (String) element.get("ESPECIALIDAD") : null;
            String nombre =  element.get("NOMBRE") != null ? (String) element.get("NOMBRE") : null;
            LocalDateTime fecha_insercion =  element.get("FECHA_INSERCION") != null ? (LocalDateTime) element.get("FECHA_INSERCION") : null;
            String usuario_insercion =  element.get("USUARIO_INSERCION") != null ? (String) element.get("USUARIO_INSERCION") : null;
            LocalDateTime fecha_modificacion =  element.get("FECHA_MODIFICACION") != null ? (LocalDateTime) element.get("FECHA_MODIFICACION") : null;
            String usuario_modificacion = element.get("USUARIO_MODIFICACION") != null ? (String) element.get("USUARIO_MODIFICACION") : null;
            Medico.add(new Medico(
                    id_medico,
                    usuario_fk,
                    especialidad,
                    nombre,
                    fecha_insercion,
                    usuario_insercion,
                    fecha_modificacion,
                    usuario_modificacion
            ));
        }
        return new MedicosListRows(Medico);
    }

    @Override
    public Boolean InsertMedico(Medico medico) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(medico);
        medicosRepository.InsertMedico(hmpEntitie);
        return true;
    }

    @Override
    public Boolean UpdateMedico(Integer id_medico,Medico medico) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(medico);
        medicosRepository.UpdateMedico(id_medico,hmpEntitie);
        return true;
    }

    @Override
    public Medico getMedicoById(Integer id_medico) {
    LinkedHashMap<String,Object> hmpMedico = medicosRepository.getMedicoById(id_medico);

    Integer usuario_fk =  hmpMedico.get("USUARIO_FK") != null ? (int) hmpMedico.get("USUARIO_FK") : null;
    String especialidad = hmpMedico.get("ESPECIALIDAD") != null ? (String) hmpMedico.get("ESPECIALIDAD") : null;
    String nombre =  hmpMedico.get("NOMBRE") != null ? (String) hmpMedico.get("NOMBRE") : null;
    LocalDateTime fecha_insercion =  hmpMedico.get("FECHA_INSERCION") != null ? (LocalDateTime) hmpMedico.get("FECHA_INSERCION") : null;
    String usuario_insercion =  hmpMedico.get("USUARIO_INSERCION") != null ? (String) hmpMedico.get("USUARIO_INSERCION") : null;
    LocalDateTime fecha_modificacion =  hmpMedico.get("FECHA_MODIFICACION") != null ? (LocalDateTime) hmpMedico.get("FECHA_MODIFICACION") : null;
    String usuario_modificacion = hmpMedico.get("USUARIO_MODIFICACION") != null ? (String) hmpMedico.get("USUARIO_MODIFICACION") : null;
        return new Medico(
                id_medico,
                usuario_fk,
                especialidad,
                nombre,
                fecha_insercion,
                usuario_insercion,
                fecha_modificacion,
                usuario_modificacion
                );
    }
}
