package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;

import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.HistoriaClinicaService;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.HistoriaClinicaListRows;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("historia-clinica")
public class HistoriaClinicaController {
    private final HistoriaClinicaService historiaClinicaService;
    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService) {this.historiaClinicaService = historiaClinicaService;}

    @GetMapping("lista-historia-clinica")
    public ResponseEntity<HistoriaClinicaListRows> getHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        return ResponseEntity.ok(historiaClinicaService.getListHistoriaClinicas(historiaClinica));
    }

    @PostMapping("crear-historia-clinica")
    public ResponseEntity<?> createHistoriaClinica(@Valid @RequestBody HistoriaClinica historiaClinica) {
        return ResponseEntity.ok(historiaClinicaService.InsertHistoriaClinica(historiaClinica)); //me retorna un boleano
    }

    @PutMapping("actualizar-historia-clinica/{id_historia_clinica}")
    public ResponseEntity<?> updateHistoriaClinica(@PathVariable Integer id_historia_clinica,@RequestBody HistoriaClinica historiaClinica) {
        return ResponseEntity.ok(historiaClinicaService.UpdateHistoriaClinica(id_historia_clinica, historiaClinica));
    }
    @GetMapping("obtener-medico-id")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@RequestParam Integer id_historia_clinica) {
        return ResponseEntity.ok(historiaClinicaService.getHistoriaClinicaById(id_historia_clinica));
    }
}
