package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.ClinicaSaludable.ClinicaSaludable.exceptions.ResourceNotFoundException;
import com.ClinicaSaludable.ClinicaSaludable.exceptions.SqlDataException;
import com.gerleinco.databasemanager.QueryBuilder;
import com.gerleinco.databasemanager.QueryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CitasMedicasRepositoryImpl implements CitasMedicasRepository {
private final QueryManager connection;
public CitasMedicasRepositoryImpl(QueryManager queryManager) {this.connection = queryManager;}
    @Override
    public LinkedList<LinkedHashMap<String, Object>> CitasMedicasList(LinkedHashMap<String, Object> hmpParameters) {
        QueryBuilder query = new QueryBuilder("CITAS_MEDICAS")
                .setField("ID_CITA_MEDICA")
                .setField("PACIENTES_FK")
                .setField("MEDICO_FK")
                .setField("FECHA_CITA")
                .setField("ESTADO_CITA")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de cita medica");
        }
        return resultSet;
    }

    @Override
    public LinkedHashMap<String, Object> InsertCitasMedica(LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","CITAS_MEDICAS");
        LinkedHashMap<String, Object> insert  = connection.OperacionBD(hmpEntitie,"INSERTAR");
        //   System.out.println(insert);
        if (insert.containsKey("REGISTROS") && insert.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(insert.get("REGISTROS").toString());
        }
        return insert;
    }

    @Override
    public LinkedHashMap<String, Object> UpdateCitasMedica(Integer id_cita_medica, LinkedHashMap<String, Object> hmpEntitie) {
        hmpEntitie.put("TABLABD","CITAS_MEDICAS");
        LinkedHashMap<String, Object> hmpWHEREBD = new LinkedHashMap<>();

        hmpWHEREBD.put("ID_CITA_MEDICA", id_cita_medica);
        hmpEntitie.put("WHEREBD", hmpWHEREBD);

        LinkedHashMap<String, Object> update  = connection.OperacionBD(hmpEntitie,"ACTUALIZAR");
        //  System.out.println(update);
        if (update.containsKey("REGISTROS") && update.get("REGISTROS").toString().contains("ERROR")) {
            throw new SqlDataException(update.get("REGISTROS").toString());
        }
        return update;
    }

    @Override
    public LinkedHashMap<String, Object> getCitaMedicaById(Integer id_cita_medica) {
        QueryBuilder query = new QueryBuilder("CITAS_MEDICAS")
                .setField("ID_CITA_MEDICA")
                .setField("PACIENTES_FK")
                .setField("MEDICO_FK")
                .setField("FECHA_CITA")
                .setField("ESTADO_CITA")
                .setField("FECHA_INSERCION")
                .setField("USUARIO_INSERCION")
                .setField("FECHA_MODIFICACION")
                .setField("USUARIO_MODIFICACION");
        if (id_cita_medica == null && id_cita_medica < 0) {
            throw new NoSuchElementException("no contiene id, es nulo o es negativo");
        }
        query = query.setWhereDefaultCondition("ID_CITA_MEDICA")
                .setValueWhere(id_cita_medica);
        LinkedList<LinkedHashMap<String,Object>> resultSet = connection.Consultar(query.getQueryObject());

        if (resultSet == null || resultSet.isEmpty()) {
            throw new ResourceNotFoundException("no se encontro la lista de citas medicas");
        }
        return resultSet.getFirst();
    }
}
