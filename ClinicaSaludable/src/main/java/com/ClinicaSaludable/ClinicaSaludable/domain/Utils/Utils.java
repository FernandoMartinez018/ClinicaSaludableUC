package com.ClinicaSaludable.ClinicaSaludable.domain.Utils;

import java.lang.reflect.RecordComponent;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static <T extends Record> LinkedHashMap<String, Object> convertRecordToLinkedHash(T record) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (record != null) {
            for (RecordComponent component : record.getClass().getRecordComponents()) {
                try {
                    map.put(component.getName().toUpperCase(), component.getAccessor().invoke(record));
                } catch (Exception e) {
                    throw new RuntimeException("Error al convertir record a Map", e);
                }
            }
        }
        return map;
    }

    public static <T extends Record> LinkedList<LinkedHashMap<String, Object>> convertListToMapList(List<T> records) {
        if (records == null || records.isEmpty()) {
            return new LinkedList<>();
        }

        return records.stream()
                .map(Utils::convertRecordToLinkedHash)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
