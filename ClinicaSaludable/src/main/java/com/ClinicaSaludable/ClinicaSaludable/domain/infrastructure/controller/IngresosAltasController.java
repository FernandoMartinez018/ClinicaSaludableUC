package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;

import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.IngresosAltasService;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltas;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.IngresosAltasListRows;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("ingresos-altas")
public class IngresosAltasController {
    private final IngresosAltasService ingresosAltasService;
    public IngresosAltasController(IngresosAltasService ingresosAltasService) {this.ingresosAltasService = ingresosAltasService;}

    @GetMapping("lista-ingresos-altas")
    public ResponseEntity<IngresosAltasListRows> getIngresosAltas(IngresosAltas ingresosAltas) {
        return ResponseEntity.ok(ingresosAltasService.getListIngresosAltas(ingresosAltas));
    }

    @PostMapping("crear-ingreso-altas")
    public ResponseEntity<?> createIngresoAltas(@Valid @RequestBody IngresosAltas ingresosAltas) {
        return ResponseEntity.ok(ingresosAltasService.InsertIngresoAltas(ingresosAltas)); //me retorna un boleano
    }

    @PutMapping("actualizar-ingreso-altas/{id_ingreso_altas}")
    public ResponseEntity<?> updateIngresoAltas(@PathVariable Integer id_ingreso_altas,@RequestBody IngresosAltas ingresosAltas) {
        return ResponseEntity.ok(ingresosAltasService.UpdateIngresoAltas(id_ingreso_altas, ingresosAltas));
    }
    @GetMapping("obtener-ingreso-altas-id")
    public ResponseEntity<IngresosAltas> getIngresoAltasById(@RequestParam Integer id_ingreso_altas) {
        return ResponseEntity.ok(ingresosAltasService.getIngresoAltasById(id_ingreso_altas));
    }
}
