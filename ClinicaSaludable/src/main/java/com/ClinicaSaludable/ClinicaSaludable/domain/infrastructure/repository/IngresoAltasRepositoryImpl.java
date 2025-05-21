package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class IngresoAltasRepositoryImpl implements IngresoAltasRepository {
    private final QueryManager connection;
    public IngresoAltasRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}

    @Override
    public LinkedList<LinkedHashMap<String, Object>> IngresoAltasList(LinkedHashMap<String, Object> hmpParameters) {
        QueryBuilder query = new QueryBuilder("INGRESOS_ALTAS")
                .setField("ID_INGRESO_ALTAS")
                .setField("PACIENTES_FK")
                .setField("CAMAS_FK")
                .setField("FECHA_INGRESO")
                .setField("FECHA_ALTA")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());
        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de ingresos altas");
        }
        return resultSet;
    }

    @Override
    public LinkedHashMap<String, Object> InsertIngresoAltas(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","INGRESOS_ALTAS");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
        //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateIngresoAltas(Integer id_medico, LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","INGRESOS_ALTAS");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_INGRESO_ALTAS", id_medico);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
        //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }

    @Override
    public LinkedHashMap<String, Object> getIngresoAltasById(Integer id_ingreso_altas) {
        QueryBuilder query = new QueryBuilder("INGRESOS_ALTAS")
                .setField("ID_INGRESO_ALTAS")
                .setField("PACIENTES_FK")
                .setField("CAMAS_FK")
                .setField("FECHA_INGRESO")
                .setField("FECHA_ALTA")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_ingreso_altas == null && id_ingreso_altas < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_INGRESO_ALTAS")
                .setValueWhere(id_ingreso_altas);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de ingresos altas");
        }
        return resultSet.getFirst();
    }
}
