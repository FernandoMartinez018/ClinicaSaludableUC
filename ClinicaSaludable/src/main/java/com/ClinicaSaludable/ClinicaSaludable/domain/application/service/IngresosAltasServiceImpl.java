package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.IngresoAltasRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltas;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltasListRows;

public class IngresosAltasServiceImpl implements IngresosAltasService {
    private IngresoAltasRepository ingresoAltasRepository;
    public IngresosAltasServiceImpl(IngresoAltasRepository ingresoAltasRepository) {this.ingresoAltasRepository = ingresoAltasRepository;}

    @Override
    public IngresosAltasListRows getListIngresosAltas(IngresosAltas ingresosAltas) {
        return null;
    }

    @Override
    public Boolean InsertIngresoAltas(IngresosAltas ingresosAltas) {
        return null;
    }

    @Override
    public Boolean UpdateIngresoAltas(Integer id_ingreso_altas, IngresosAltas ingresosAltas) {
        return null;
    }

    @Override
    public IngresosAltas getIngresoAltasById(Integer id_ingreso_altas) {
        return null;
    }
}
