package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.sql.SQLDataException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MedicosRepositoryImpl implements MedicosRepository {
    private final QueryManager connection;
    public MedicosRepositoryImpl(QueryManager queryManager) {
        this.connection = queryManager;
    }

    @Override
    public LinkedList<LinkedHashMap<String, Object>> MedicosList(LinkedHashMap<String, Object> hmpParameters) {
        QueryBuilder query = new QueryBuilder("MEDICOS M")
                .setField("ID_MEDICO")
                .setField("USUARIO_FK")
                .setField("ESPECIALIDAD")
                .setField("NOMBRE")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (hmpParameters.containsKey("id_medico") && hmpParameters.get("id_medico") != null && !hmpParameters.get("id_medico").toString().isEmpty()){
            query = query.setWhereDefaultCondition("ID_MEDICO")
                    .setValueWhere(hmpParameters.get("id_medico"));

        }

        LinkedList<LinkedHashMap<String, Object>> resultSet = connection.Consultar(query.getQueryObject());
      //  System.out.println("resultSet = " + resultSet);
        if (resultSet == null) {
            throw new ResourceNotFoundException("no se encontro la lista de medicos");
        }

        return resultSet;
    }

    @Override
    public LinkedHashMap<String, Object> InsertMedico(LinkedHashMap<String,Object> hmpEntitie)  {
        hmpEntitie.put("TABLABD","MEDICOS");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
     //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateMedico(Integer id_medico,LinkedHashMap<String, Object> hmpEntitie){
        hmpEntitie.put("TABLABD","MEDICOS");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_MEDICO", id_medico);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
      //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }
    @Override
    public LinkedHashMap<String, Object> getMedicoById(Integer id_Medico) {
        QueryBuilder query = new QueryBuilder("MEDICOS M")
                .setField("ID_MEDICO")
                .setField("USUARIO_FK")
                .setField("ESPECIALIDAD")
                .setField("NOMBRE")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_Medico == null && id_Medico < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_MEDICO")
                .setValueWhere(id_Medico);

        LinkedList<LinkedHashMap<String, Object>> resultSet = connection.Consultar(query.getQueryObject());
          System.out.println("resultSet = " + resultSet);
        if (resultSet == null) {
            throw new ResourceNotFoundException("no se encontro la lista de medicos");
        }

        return resultSet.getFirst();
    }
}
