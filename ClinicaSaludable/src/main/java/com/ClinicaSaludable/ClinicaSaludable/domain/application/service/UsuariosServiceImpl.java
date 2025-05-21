package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.Utils.Utils;
import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.UsuariosRepository;
import com.ClinicaSaludable.ClinicaSaludable.domain.model.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class UsuariosServiceImpl implements UsuariosService{
    private final UsuariosRepository usuariosRepository;
    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {this.usuariosRepository = usuariosRepository;}

    @Override
    public Object InsertUsuario(Usuario usuario) throws NoSuchAlgorithmException {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(usuario);
        hmpEntitie.put("PASSWORD",EncriptarPsw(hmpEntitie.get("PASSWORD").toString()));
        usuariosRepository.InsertUsuarios(hmpEntitie);
        hmpEntitie.remove("PASSWORD");
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Object UpdateUsuario(Integer id_usuario, Usuario usuario) {
        LinkedHashMap<String, Object> hmpEntitie = Utils.convertRecordToLinkedHash(usuario);
        usuariosRepository.UpdateUsuarios(id_usuario,hmpEntitie);
        hmpEntitie.put("status",true);
        return hmpEntitie;
    }

    @Override
    public Usuario getUsuarioById(Integer id_usuario) {
        LinkedHashMap<String,Object> hmpUser = usuariosRepository.getUsuariosById(id_usuario);
        String nombre = hmpUser.get("NOMBRE") != null ? hmpUser.get("NOMBRE").toString() : "";
        String email = hmpUser.get("EMAIL") != null ? hmpUser.get("EMAIL").toString() : "";
        String rol = hmpUser.get("ROL") != null ? hmpUser.get("ROL").toString() : "";
        LocalDateTime fecha_insercion = hmpUser.get("FECHA_INSERCION") != null ? LocalDateTime.parse(hmpUser.get("FECHA_INSERCION").toString()) : null;
        String usuario_insercion = hmpUser.get("USUARIO_INSERCION")  != null ? hmpUser.get("USUARIO_INSERCION").toString() : "";
        LocalDateTime fecha_modificacion = hmpUser.get("FECHA_MODIFICACION") != null ? LocalDateTime.parse(hmpUser.get("FECHA_MODIFICACION").toString()) : null;
        String usuario_modificacion = hmpUser.get("USUARIO_MODIFICACION") != null ? hmpUser.get("USUARIO_MODIFICACION").toString() : "";

        return new Usuario(
          id_usuario,
          nombre,
          email,
          "",
          rol,
          fecha_insercion,
          usuario_insercion,
          fecha_modificacion,
          usuario_modificacion
        );
    }

    @Override
    public Object validarUsuario(String email, String password) throws NoSuchAlgorithmException {
        LinkedHashMap<String,Object> hmpUser = usuariosRepository.validateUser(email);
        hmpUser.put("mensaje","Validacion correcta");
        hmpUser.put("status",true);
        if (!hmpUser.get("PASSWORD").toString().equalsIgnoreCase(EncriptarPsw(password))) {
            hmpUser.put("mensaje","Contraseña incorrecta por favor intentelo de nuevo");
            hmpUser.put("status",false);
        }
        return hmpUser;
    }

    public String EncriptarPsw(String sPsw) throws NoSuchAlgorithmException {
        MessageDigest mdgAlgoritmo = MessageDigest.getInstance("MD5");
        mdgAlgoritmo.update(sPsw.getBytes());
        byte[] bytes = mdgAlgoritmo.digest();
        StringBuilder sblClave = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sblClave.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sblClave.toString();
    }
}
