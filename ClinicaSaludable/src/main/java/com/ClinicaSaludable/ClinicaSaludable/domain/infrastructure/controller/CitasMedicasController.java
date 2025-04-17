package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;

import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.CitasMedicasService;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedica;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.CitaMedicaListRows;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("citas-medicas")
public class CitasMedicasController {
    private final CitasMedicasService citasMedicasService;
    public CitasMedicasController(CitasMedicasService citasMedicasService) {this.citasMedicasService = citasMedicasService;}

    @GetMapping("lista-citas-medicas")
    public ResponseEntity<CitaMedicaListRows> getCitasMedicas(CitaMedica citaMedica) {
        return ResponseEntity.ok(citasMedicasService.getListCitasMedicas(citaMedica));
    }

    @PostMapping("crear-cita-medica")
    public ResponseEntity<?> createCitaMedica(@Valid @RequestBody CitaMedica citaMedica) {
        return ResponseEntity.ok(citasMedicasService.InsertCitaMedica(citaMedica)); //me retorna un boleano
    }

    @PutMapping("actualizar-cita-medica/{id_cita_medica}")
    public ResponseEntity<?> updateCitaMedica(@PathVariable Integer id_cita_medica,@RequestBody CitaMedica citaMedica) {
        return ResponseEntity.ok(citasMedicasService.UpdateCitaMedica(id_cita_medica, citaMedica));
    }
    @GetMapping("obtener-cita-medica-id")
    public ResponseEntity<CitaMedica> getCitaMedicaById(@RequestParam Integer id_cita_medica) {
        return ResponseEntity.ok(citasMedicasService.getCitaMedicaById(id_cita_medica));
    }
}
