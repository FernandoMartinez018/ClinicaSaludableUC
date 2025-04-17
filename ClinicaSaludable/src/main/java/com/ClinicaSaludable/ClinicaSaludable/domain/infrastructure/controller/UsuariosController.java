package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.controller;

import com.ClinicaSaludable.ClinicaSaludable.domain.application.service.UsuariosService;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Medico;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.MedicosListRows;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;
    public UsuariosController(UsuariosService usuariosService) {this.usuariosService = usuariosService;}

//    @GetMapping("lista-medicos")
//    public ResponseEntity<MedicosListRows> getMedicos(Medico medico) {
//        return ResponseEntity.ok(medicosService.getListMedicos(medico));
//    }

    @PostMapping("crear-usuario")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuariosService.InsertUsuario(usuario)); //me retorna un boleano
    }

    @PutMapping("actualizar-usuario/{id_usuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer id_usuario,@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuariosService.UpdateUsuario(id_usuario, usuario));
    }
    @GetMapping("obtener-usuario-id")
    public ResponseEntity<Usuario> getUsuarioById(@RequestParam Integer id_usuario) {
        return ResponseEntity.ok(usuariosService.getUsuarioById(id_usuario));
    }
}
