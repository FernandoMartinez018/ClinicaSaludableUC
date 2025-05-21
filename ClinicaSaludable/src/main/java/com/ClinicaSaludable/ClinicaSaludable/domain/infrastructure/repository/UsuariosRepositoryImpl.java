package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class UsuariosRepositoryImpl implements UsuariosRepository {
    private final QueryManager connection;
    public UsuariosRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> UsuariosList(LinkedHashMap<String, Object> hmpParameters) {
        QueryBuilder query = new QueryBuilder("USUARIOS")
                .setField("ID_USUARIO")
                .setField("NOMBRE")
                .setField("EMAIL")
                .setField("PASSWORD")
                .setField("ROL")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());
        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de usuarios");
        }
        return resultSet;
    }

    @Override
    public LinkedHashMap<String, Object> InsertUsuarios(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","USUARIOS");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
        //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateUsuarios(Integer id_usuario, LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","USUARIOS");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_USUARIO", id_usuario);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
        //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }

    @Override
    public LinkedHashMap<String, Object> getUsuariosById(Integer id_usuario) {
        QueryBuilder query = new QueryBuilder("USUARIOS")
                .setField("ID_USUARIO")
                .setField("NOMBRE")
                .setField("EMAIL")
                .setField("PASSWORD")
                .setField("ROL")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_usuario == null && id_usuario < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_USUARIO")
                .setValueWhere(id_usuario);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de usuarios");
        }
        return resultSet.getFirst();
    }

    @Override
    public LinkedHashMap<String, Object> validateUser(String email) {
        QueryBuilder query = new QueryBuilder("USUARIOS")
                .setField("ID_USUARIO")
                .setField("NOMBRE")
                .setField("EMAIL")
                .setField("PASSWORD")
                .setField("ROL");
        if (email == null && email.isBlank()) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("EMAIL")
                .setValueWhere(email);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());
        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro el email del usuario");
        }
        return resultSet.getFirst();
    }
}
