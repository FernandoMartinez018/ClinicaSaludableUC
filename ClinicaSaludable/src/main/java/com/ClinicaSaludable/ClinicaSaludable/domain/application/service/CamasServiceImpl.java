package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.CamasRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Cama;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CamasListRows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CamasServiceImpl implements CamasService {
    private CamasRepository camasRepository;
    public CamasServiceImpl(CamasRepository camasRepository) {this.camasRepository = camasRepository;}

    @Override
    public CamasListRows getListCamas(Cama cama) {
        LinkedList<LinkedHashMap<String,Object>> hmpCamas = camasRepository.CamasList(Utils.convertRecordToLinkedHash(cama));
        List<Cama> Cama = new ArrayList<>();
        for (LinkedHashMap<String,Object> element : hmpCamas) {
            Integer id_cama = element.get("ID_CAMA") != null ? (Integer) element.get("ID_CAMA") : null;
            Integer numero_cama = element.get("NUMERO_CAMA") != null ? (Integer) element.get("NUMERO_CAMA") : null;
            String estado = element.get("ESTADO") != null ? (String) element.get("ESTADO") : null;
            LocalDateTime fecha_disponibilidad = element.get("FECHA_DISPONIBILIDAD") != null ? LocalDateTime.parse(element.get("FECHA_DISPONIBILIDAD").toString()) : null;
            LocalDateTime fecha_insercion =  element.get("FECHA_INSERCION") != null ? (LocalDateTime) element.get("FECHA_INSERCION") : null;
            String usuario_insercion =  element.get("USUARIO_INSERCION") != null ? (String) element.get("USUARIO_INSERCION") : null;
            LocalDateTime fecha_modificacion =  element.get("FECHA_MODIFICACION") != null ? (LocalDateTime) element.get("FECHA_MODIFICACION") : null;
            String usuario_modificacion = element.get("USUARIO_MODIFICACION") != null ? (String) element.get("USUARIO_MODIFICACION") : null;

            Cama.add(new Cama(
                    id_cama,
                    numero_cama,
                    estado,
                    fecha_disponibilidad,
                    fecha_insercion,
                    usuario_insercion,
                    fecha_modificacion,
                    usuario_modificacion,
                    null,
                    null
            ));
        }
        return new CamasListRows(Cama);
    }

    @Override
    public Object InsertCama(Cama cama) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(cama);
        camasRepository.InsertCama(hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Object UpdateCama(Integer id_cama, Cama cama) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(cama);
        camasRepository.UpdateCama(id_cama,hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Cama getCamaById(Integer id_cama) {
        LinkedHashMap<String,Object> hmpCama = camasRepository.getCamaById(id_cama);
        Integer numero_cama = hmpCama.get("NUMERO_CAMA") != null ? (Integer) hmpCama.get("NUMERO_CAMA") : null;
        String estado = hmpCama.get("ESTADO") != null ? (String) hmpCama.get("ESTADO") : null;
        LocalDateTime fecha_disponibilidad = hmpCama.get("FECHA_DISPONIBILIDAD") != null ? LocalDateTime.parse(hmpCama.get("FECHA_DISPONIBILIDAD").toString()) : null;
        LocalDateTime fecha_insercion =  hmpCama.get("FECHA_INSERCION") != null ? (LocalDateTime) hmpCama.get("FECHA_INSERCION") : null;
        String usuario_insercion =  hmpCama.get("USUARIO_INSERCION") != null ? (String) hmpCama.get("USUARIO_INSERCION") : null;
        LocalDateTime fecha_modificacion =  hmpCama.get("FECHA_MODIFICACION") != null ? (LocalDateTime) hmpCama.get("FECHA_MODIFICACION") : null;
        String usuario_modificacion = hmpCama.get("USUARIO_MODIFICACION") != null ? (String) hmpCama.get("USUARIO_MODIFICACION") : null;
        return new Cama(
                id_cama,
                numero_cama,
                estado,
                fecha_disponibilidad,
                fecha_insercion,
                usuario_insercion,
                fecha_modificacion,
                usuario_modificacion,
                null,
                null
        );
    }
}
