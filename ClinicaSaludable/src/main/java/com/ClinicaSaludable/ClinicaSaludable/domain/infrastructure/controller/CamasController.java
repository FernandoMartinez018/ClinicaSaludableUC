package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;

import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.CamasService;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Cama;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CamasListRows;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("camas")
public class CamasController {
    private final CamasService camasService;
    public CamasController(CamasService camasService) {this.camasService = camasService;}

    @GetMapping("lista-camas")
    public ResponseEntity<CamasListRows> getCamas(Cama cama) {
        return ResponseEntity.ok(camasService.getListCamas(cama));
    }

    @PostMapping("crear-cama")
    public ResponseEntity<?> createCama(@Valid @RequestBody Cama cama) {
        return ResponseEntity.ok(camasService.InsertCama(cama)); //me retorna un boleano
    }

    @PutMapping("actualizar-cama/{id_cama}")
    public ResponseEntity<?> updateMedico(@PathVariable Integer id_cama,@RequestBody Cama cama) {
        return ResponseEntity.ok(camasService.UpdateCama(id_cama, cama));
    }
    @GetMapping("obtener-cama-id")
    public ResponseEntity<Cama> getMedicoById(@RequestParam Integer id_cama) {
        return ResponseEntity.ok(camasService.getCamaById(id_cama));
    }
}
