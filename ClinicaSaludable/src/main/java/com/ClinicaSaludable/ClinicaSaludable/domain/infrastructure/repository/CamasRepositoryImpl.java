package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CamasRepositoryImpl implements CamasRepository {

    private final QueryManager connection;

    public CamasRepositoryImpl(QueryManager queryManager) {
        this.connection = queryManager;
    }

    @Override
    public LinkedList<LinkedHashMap<String, Object>> CamasList(LinkedHashMap<String, Object> hmpParameters) {
        System.out.println("hmpParameters = " + hmpParameters);
        QueryBuilder query = new QueryBuilder("CAMAS")
                .setField("ID_CAMA")
                .setField("NUMERO_CAMA")
                .setField("ESTADO")
                .setField("FECHA_DISPONIBILIDAD")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (hmpParameters.containsKey("FECHA_INICIO_CITA") && hmpParameters.get("FECHA_INICIO_CITA") !=null &&
        !hmpParameters.get("FECHA_INICIO_CITA").toString().isEmpty()) {
            query = query.WherePredeterminadeCondition("FECHA_DISPONIBILIDAD", ">=")
                    .setValueWhere(hmpParameters.get("FECHA_INICIO_CITA"));
        }
        if (hmpParameters.containsKey("FECHA_FINAL_CITA") && hmpParameters.get("FECHA_FINAL_CITA") !=null
                && !hmpParameters.get("FECHA_FINAL_CITA").toString().isEmpty()) {
            query = query.WherePredeterminadeCondition("FECHA_DISPONIBILIDAD", ">=")
                    .setValueWhere(hmpParameters.get("FECHA_FINAL_CITA"));
        }
        if (hmpParameters.containsKey("ESTADO") && hmpParameters.get("ESTADO") !=null
                && hmpParameters.get("ESTADO").toString().isEmpty()){
            query = query.setWhereDefaultCondition("ESTADO")
                    .setValueWhere(hmpParameters.get("ESTADO"));
        }
        System.out.println("query = " + query);
        LinkedList<LinkedHashMap<String, Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de camas");
        }
        return resultSet;
    }

    @Override
    public LinkedHashMap<String, Object> InsertCama(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","CAMAS");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
        //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateCama(Integer id_cama, LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","CAMAS");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_CAMA", id_cama);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
        //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }

    @Override
    public LinkedHashMap<String, Object> getCamaById(Integer id_cama) {
        QueryBuilder query = new QueryBuilder("CAMAS")
                .setField("ID_CAMA")
                .setField("NUMERO_CAMA")
                .setField("ESTADO")
                .setField("FECHA_DISPONIBILIDAD")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_cama == null && id_cama < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_CAMA")
                .setValueWhere(id_cama);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de camas");
        }
        return resultSet.getFirst();
    }
}
