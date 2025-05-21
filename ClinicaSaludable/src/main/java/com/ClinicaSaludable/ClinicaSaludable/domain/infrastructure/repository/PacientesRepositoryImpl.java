package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PacientesRepositoryImpl implements PacientesRepository {
    private final QueryManager connection;
    public PacientesRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}
    @Override
    public LinkedList<LinkedHashMap<String, Object>> PacientesList(LinkedHashMap<String, Object> hmpParameters) {
        QueryBuilder query = new QueryBuilder("PACIENTES")
                .setField("ID_PACIENTE")
                .setField("USUARIO_FK")
                .setField("FECHA_NACIMIENTO")
                .setField("DIRECCION")
                .setField("TELEFONO_CONTACTO")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());
        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de pacientes");
        }
        return resultSet;
    }

    @Override
    public LinkedHashMap<String, Object> InsertPaciente(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","PACIENTES");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
        //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdatePaciente(Integer id_paciente, LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","PACIENTES");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_PACIENTE", id_paciente);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
        //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }

    @Override
    public LinkedHashMap<String, Object> getPacienteById(Integer id_paciente) {
        QueryBuilder query = new QueryBuilder("PACIENTES")
                .setField("ID_PACIENTE")
                .setField("USUARIO_FK")
                .setField("FECHA_NACIMIENTO")
                .setField("DIRECCION")
                .setField("TELEFONO_CONTACTO")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_paciente == null && id_paciente < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_PACIENTE")
                .setValueWhere(id_paciente);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de pacientes");
        }
        return resultSet.getFirst();
    }
}
