package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.IngresoAltasRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltas;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltasListRows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class IngresosAltasServiceImpl implements IngresosAltasService {
    private IngresoAltasRepository ingresoAltasRepository;
    public IngresosAltasServiceImpl(IngresoAltasRepository ingresoAltasRepository) {this.ingresoAltasRepository = ingresoAltasRepository;}

    @Override
    public IngresosAltasListRows getListIngresosAltas(IngresosAltas ingresosAltas) {
        LinkedList<LinkedHashMap<String,Object>> hmpIngresosAltas = ingresoAltasRepository.IngresoAltasList(Utils.convertRecordToLinkedHash(ingresosAltas));
        List<IngresosAltas> IngresosAltas = new ArrayList<>();
        for(LinkedHashMap<String,Object> element : hmpIngresosAltas){
            Integer id_ingresos_altas = element.get("ID_INGRESO_ALTAS") != null ? Integer.parseInt(element.get("ID_INGRESO_ALTAS").toString()) : null;
            Integer pacientes_fk = element.get("PACIENTES_FK") != null ? Integer.parseInt(element.get("PACIENTES_FK").toString()) : null;
            Integer camas_fk = element.get("CAMAS_FK") != null ? Integer.parseInt(element.get("CAMAS_FK").toString()) : null;
            LocalDateTime fecha_ingreso = element.get("FECHA_INGRESO") != null ? LocalDateTime.parse(element.get("FECHA_INGRESO").toString()) : null;
            LocalDateTime fecha_alta = element.get("FECHA_ALTA") != null ? LocalDateTime.parse(element.get("FECHA_ALTA").toString()) : null;
            LocalDateTime fecha_insercion =  element.get("FECHA_INSERCION") != null ? (LocalDateTime) element.get("FECHA_INSERCION") : null;
            String usuario_insercion =  element.get("USUARIO_INSERCION") != null ? (String) element.get("USUARIO_INSERCION") : null;
            LocalDateTime fecha_modificacion =  element.get("FECHA_MODIFICACION") != null ? (LocalDateTime) element.get("FECHA_MODIFICACION") : null;
            String usuario_modificacion = element.get("USUARIO_MODIFICACION") != null ? (String) element.get("USUARIO_MODIFICACION") : null;
            IngresosAltas.add(new IngresosAltas(
                    id_ingresos_altas,
                    pacientes_fk,
                    camas_fk,
                    fecha_ingreso,
                    fecha_alta,
                    fecha_insercion,
                    usuario_insercion,
                    fecha_modificacion,
                    usuario_modificacion
            ));
        }
        return new IngresosAltasListRows(IngresosAltas);
    }

    @Override
    public Object InsertIngresoAltas(IngresosAltas ingresosAltas) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(ingresosAltas);
        ingresoAltasRepository.InsertIngresoAltas(hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Object UpdateIngresoAltas(Integer id_ingreso_altas, IngresosAltas ingresosAltas) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(ingresosAltas);
        ingresoAltasRepository.UpdateIngresoAltas(id_ingreso_altas,hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public IngresosAltas getIngresoAltasById(Integer id_ingreso_altas) {
        LinkedHashMap<String,Object> hmpIngresoAltas = ingresoAltasRepository.getIngresoAltasById(id_ingreso_altas);
        Integer pacientes_fk = hmpIngresoAltas.get("PACIENTES_FK") != null ? Integer.parseInt(hmpIngresoAltas.get("PACIENTES_FK").toString()) : null;
        Integer camas_fk = hmpIngresoAltas.get("CAMAS_FK") != null ? Integer.parseInt(hmpIngresoAltas.get("CAMAS_FK").toString()) : null;
        LocalDateTime fecha_ingreso = hmpIngresoAltas.get("FECHA_INGRESO") != null ? LocalDateTime.parse(hmpIngresoAltas.get("FECHA_INGRESO").toString()) : null;
        LocalDateTime fecha_alta = hmpIngresoAltas.get("FECHA_ALTA") != null ? LocalDateTime.parse(hmpIngresoAltas.get("FECHA_ALTA").toString()) : null;
        LocalDateTime fecha_insercion =  hmpIngresoAltas.get("FECHA_INSERCION") != null ? (LocalDateTime) hmpIngresoAltas.get("FECHA_INSERCION") : null;
        String usuario_insercion =  hmpIngresoAltas.get("USUARIO_INSERCION") != null ? (String) hmpIngresoAltas.get("USUARIO_INSERCION") : null;
        LocalDateTime fecha_modificacion =  hmpIngresoAltas.get("FECHA_MODIFICACION") != null ? (LocalDateTime) hmpIngresoAltas.get("FECHA_MODIFICACION") : null;
        String usuario_modificacion = hmpIngresoAltas.get("USUARIO_MODIFICACION") != null ? (String) hmpIngresoAltas.get("USUARIO_MODIFICACION") : null;
    return new IngresosAltas(
            id_ingreso_altas,
            pacientes_fk,
            camas_fk,
            fecha_ingreso,
            fecha_alta,
            fecha_insercion,
            usuario_insercion,
            fecha_modificacion,
            usuario_modificacion
    );
    }
}
