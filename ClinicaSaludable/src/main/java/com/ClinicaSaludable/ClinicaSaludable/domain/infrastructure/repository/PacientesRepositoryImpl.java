package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class PacientesRepositoryImpl implements PacientesRepository {
    private final QueryManager connection;
    public PacientesRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}
    @Override
    public LinkedList<LinkedHashMap<String, Object>> PacientesList(LinkedHashMap<String, Object> hmpParameters) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> InsertPaciente(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","MEDICOS");
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
        return null;
    }
}
