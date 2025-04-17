package com.gerleinco.databasemanager;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class QueryBuilder {
    private final LinkedHashMap<String, Object> queryObject = new LinkedHashMap<>();
    private final LinkedHashMap<Integer, Object> whereValues = new LinkedHashMap<>();
    private final StringJoiner fields = new StringJoiner(", ");
    private final StringJoiner table = new StringJoiner(" ");
    private final StringJoiner where = new StringJoiner(" AND ");
    private final StringJoiner order = new StringJoiner(", ");
    private final StringJoiner group = new StringJoiner(", ");
    private int counter = 1;

    public QueryBuilder(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tabla no puede ser nulo o vacío.");
        }
        table.add(tableName);
        queryObject.put("TABLASBD", table.toString());
    }

    public QueryBuilder setField(String fieldName) {
        if (fieldName == null || fieldName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del campo no puede ser nulo o vacío.");
        }
        fields.add(fieldName);
        queryObject.put("CAMPOSBD", fields.toString());
        return this;
    }

    public QueryBuilder setField(List<String> fieldNames) {
        if (fieldNames == null || fieldNames.isEmpty()) {
            throw new IllegalArgumentException("La lista de campos no puede estar vacía o ser nula.");
        }
        fieldNames.stream().filter(f -> f != null && !f.trim().isEmpty()).forEach(fields::add);

        queryObject.put("CAMPOSBD", fields.toString());
        return this;
    }

    private void addWhere(){
        queryObject.put("WHEREBD", where.toString());
    }
    private void addWhereNoParameters(){
        queryObject.put("WHEREBDESP", where.toString());
    }

    public QueryBuilder setWhereDefaultCondition(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("La condición no puede ser nula o vacía.");
        }
        where.add(condition + " = ?");
        addWhere();
        return this;
    }

    public QueryBuilder setWhereWithoutParameter(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("setWhereWithoutCondicion");
        }
        where.add(condition);
        addWhereNoParameters();
        return this;
    }

    public QueryBuilder WherePredeterminadeCondition(String condition, String Predeterminade) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("La condición no puede ser nula o vacía.");
        }
        where.add(condition + Predeterminade + "?");
        addWhere();
        return this;
    }



    public QueryBuilder WhereInCondition(String condition,int value) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("La condición no puede ser nula o vacía.");
        }
        String resultado = IntStream.range(0, value)
                .mapToObj(i -> "?") // Generar un "?" por cada iteración
                .collect(Collectors.joining(", ", "(", ")"));

        String InCondition = condition + " IN " + resultado;
        where.add(InCondition);
        addWhere();
        return this;
    }

    public QueryBuilder setWhereDefaultCondition(List<String> conditions) {
        if (conditions == null || conditions.isEmpty()) {
            throw new IllegalArgumentException("La lista de condiciones no puede estar vacía o ser nula.");
        }
        conditions.stream().filter(c -> c != null && !c.trim().isEmpty()).forEach(cond -> where.add(cond + " = ?"));
        queryObject.put("WHEREBD", where.toString());
        return this;
    }

    public QueryBuilder setModificationTable(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tabla no puede ser nulo o vacío.");
        }
        table.add(tableName);
        queryObject.put("TABLASBD", table.toString());
        return this;
    }

    public QueryBuilder setValueWhere(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor de la condición WHERE no puede ser nulo.");
        }
        whereValues.put(counter++, value);
        queryObject.put("WHEREBD_HM", whereValues);
        return this;
    }

    public QueryBuilder setValueWhere(List<Object> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("La lista de valores WHERE no puede estar vacía o ser nula.");
        }
        values.stream().filter(v -> v != null).forEach(value -> whereValues.put(counter++, value));
        queryObject.put("WHEREBD_HM", whereValues);
        return this;
    }

    public QueryBuilder setHashValueWhere(LinkedHashMap<String,Object> hmpParameters) {
        if (hmpParameters == null || hmpParameters.isEmpty()) {
            throw new IllegalArgumentException("La lista de valores WHERE no puede estar vacía o ser nula.");
        }
        for (Map.Entry<String, Object> data : hmpParameters.entrySet()) {
            Object value = data.getValue();

            if (value != null && !value.toString().isEmpty()) {
                where.add(data.getKey() + " = ?");
                addWhere();
                whereValues.put(counter++, value);
            }
        }

        queryObject.put("WHEREBD_HM", whereValues);

        return this;
    }

    public QueryBuilder setOrderBy(String orderBy) {
        if (orderBy == null || orderBy.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor de ORDER BY no puede ser nulo o vacío.");
        }
        order.add(orderBy);
        queryObject.put("ORDERBY", order.toString());
        return this;
    }

    public QueryBuilder setOrderBy(List<String> ordersBy) {
        if (ordersBy == null || ordersBy.isEmpty()) {
            throw new IllegalArgumentException("El valor de ORDER BY no puede ser nulo o vacío.");
        }
        ordersBy.stream().filter(f -> f != null && !f.trim().isEmpty()).forEach(order::add);
        queryObject.put("ORDERBY", order.toString());
        return this;
    }

    public QueryBuilder setGroupBy(String groupBy) {
        if (groupBy == null || groupBy.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor de GROUP BY no puede ser nulo o vacío.");
        }
        group.add(groupBy);
        queryObject.put("GROUPBY", group.toString());
        return this;
    }

    public QueryBuilder setGroupBy(List<String> groupsBy) {
        if (groupsBy == null || groupsBy.isEmpty()) {
            throw new IllegalArgumentException("El valor de GROUP BY no puede ser nulo o vacío.");
        }

        groupsBy.stream().filter(f -> f != null && !f.trim().isEmpty()).map(f -> f.split("\\s+")[0]).forEach(group::add);
        this.queryObject.put("GROUPBY", group.toString());
        return this;
    }

    public LinkedHashMap<String, Object> getQueryObject() {
        if (!queryObject.containsKey("TABLASBD") || !queryObject.containsKey("CAMPOSBD")) {
            throw new IllegalStateException("El objeto de consulta no es válido: debe contener 'TABLASBD' y 'CAMPOSBD'.");
        }

        if (queryObject.containsKey("WHEREBD") && !queryObject.containsKey("WHEREBD_HM")) {
            throw new IllegalStateException("El objeto de consulta tiene una condición 'WHEREBD' pero no tiene valores en 'WHEREBD_HM'.");
        }

        if (queryObject.containsKey("WHEREBD") && queryObject.containsKey("WHEREBD_HM")) {
            String whereClause = queryObject.get("WHEREBD").toString();
            int placeholderCount = whereClause.length() - whereClause.replace("?", "").length();
            int valueCount = whereValues.size();
            if (placeholderCount != valueCount) {
                throw new IllegalStateException("El número de condiciones WHERE no coincide con el número de valores proporcionados.");
            }
        }

        if (queryObject.containsKey("WHEREBDESP")) {
            queryObject.put("WHEREBD", queryObject.remove("WHEREBDESP"));
        }

        return queryObject;
    }
    @Override
    public String toString() {
        return "QueryBuilder{" + queryObject + '}';
    }
}
