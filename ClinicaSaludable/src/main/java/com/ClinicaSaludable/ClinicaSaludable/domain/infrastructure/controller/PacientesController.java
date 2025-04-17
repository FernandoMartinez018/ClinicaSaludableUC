package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;


import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.PacientesService;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Paciente;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.PacientesListRows;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("pacientes")
public class PacientesController {
    private final PacientesService pacientesService;
    public PacientesController(PacientesService pacientesService) {this.pacientesService = pacientesService;}

    @GetMapping("lista-pacientes")
    public ResponseEntity<PacientesListRows> getPacientes(Paciente paciente) {
        return ResponseEntity.ok(pacientesService.getListPacientes(paciente));
    }

    @PostMapping("crear-medico")
    public ResponseEntity<?> createPaciente(@Valid @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacientesService.InsertPaciente(paciente)); //me retorna un boleano
    }

    @PutMapping("actualizar-paciente/{id_paciente}")
    public ResponseEntity<?> updatePaciente(@PathVariable Integer id_paciente,@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacientesService.UpdatePaciente(id_paciente, paciente));
    }
    @GetMapping("obtener-paciente-id")
    public ResponseEntity<Paciente> getPacienteById(@RequestParam Integer id_paciente) {
        return ResponseEntity.ok(pacientesService.getPacienteById(id_paciente));
    }
}
