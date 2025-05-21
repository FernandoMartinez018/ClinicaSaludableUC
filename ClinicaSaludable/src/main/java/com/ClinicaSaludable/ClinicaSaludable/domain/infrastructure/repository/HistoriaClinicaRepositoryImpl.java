package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HistoriaClinicaRepositoryImpl implements HistoriaClinicaRepository {
    private final QueryManager connection;
    public HistoriaClinicaRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> HistoriaClinicaList(LinkedHashMap<String, Object> hmpParameters) {
        QueryBuilder query = new QueryBuilder("HISTORIACLINICA")
                .setField("ID_HISTORIA_CLINICA")
                .setField("PACIENTES_FK")
                .setField("MEDICO_FK")
                .setField("FECHA_HISTORIA")
                .setField("DIAGNOSTICO")
                .setField("TRATAMIENTO")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de historia clinica");
        }
        return resultSet;
    }


    @Override
    public LinkedHashMap<String, Object> InsertHistoriaClinica(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","HISTORIACLINICA");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
        //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateHistoriaClinica(Integer id_historia_clinica, LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","HISTORIACLINICA");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_HISTORIA_CLINICA", id_historia_clinica);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
        //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }

    @Override
    public LinkedHashMap<String, Object> getHistoriaClinicaById(Integer id_historia_clinica) {
        QueryBuilder query = new QueryBuilder("HISTORIACLINICA")
                .setField("ID_HISTORIA_CLINICA")
                .setField("PACIENTES_FK")
                .setField("MEDICO_FK")
                .setField("FECHA_HISTORIA")
                .setField("DIAGNOSTICO")
                .setField("TRATAMIENTO")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_historia_clinica == null && id_historia_clinica < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_HISTORIA_CLINICA")
                .setValueWhere(id_historia_clinica);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de historia clinica");
        }
        return resultSet.getFirst();
    }
}
