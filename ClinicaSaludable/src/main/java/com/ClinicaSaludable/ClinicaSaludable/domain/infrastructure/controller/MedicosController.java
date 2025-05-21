package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;

import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.MedicosService;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.MedicosRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("medicos")
public class MedicosController {
private final MedicosService medicosService;
MedicosController(MedicosService medicosService) {
    this.medicosService = medicosService;
}

    @GetMapping("lista-medicos")
    public ResponseEntity<MedicosListRows> getMedicos(@RequestBody Medico medico) {
        return ResponseEntity.ok(medicosService.getListMedicos(medico));
    }

    @PostMapping("crear-medico")
    public ResponseEntity<?> createMedico(@Valid @RequestBody Medico medico) {
        return ResponseEntity.ok(medicosService.InsertMedico(medico)); //me retorna un boleano
    }

    @PutMapping("actualizar-medico/{id_medico}")
    public ResponseEntity<?> updateMedico(@PathVariable Integer id_medico,@RequestBody Medico medico) {
        return ResponseEntity.ok(medicosService.UpdateMedico(id_medico, medico));
    }
    @GetMapping("obtener-medico-id")
    public ResponseEntity<Medico> getMedicoById(@RequestParam Integer id_medico) {
        return ResponseEntity.ok(medicosService.getMedicoById(id_medico));
    }
}
