package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltas;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltasListRows;

public interface IngresosAltasService {
    IngresosAltasListRows getListIngresosAltas(IngresosAltas ingresosAltas);
    Object InsertIngresoAltas(IngresosAltas ingresosAltas);
    Object UpdateIngresoAltas(Integer id_ingreso_altas,IngresosAltas ingresosAltas);
    IngresosAltas getIngresoAltasById(Integer id_ingreso_altas);
}
