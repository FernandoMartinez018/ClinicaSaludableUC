package com.gerleinco.databasemanager.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ObjectsApplication {
    HashMap<String, Object> hmpSOConexiones;
    private HashMap<String, LinkedList<LinkedHashMap<String, Object>>> hmpColumnasTablas;
    private HashMap<String, String> hmpColumnasSelect;

    public HashMap<String, String> getHmpColumnasSelect() {
        return this.hmpColumnasSelect;
    }

    public void setHmpColumnasSelect(HashMap<String, String> hmpColumnasSelect) {
        this.hmpColumnasSelect = hmpColumnasSelect;
    }

    public HashMap<String, LinkedList<LinkedHashMap<String, Object>>> getHmpColumnasTablas() {
        return this.hmpColumnasTablas;
    }

    public void setHmpColumnasTablas(HashMap<String, LinkedList<LinkedHashMap<String, Object>>> hmpColumnasTablas) {
        this.hmpColumnasTablas = hmpColumnasTablas;
    }

    private ObjectsApplication() {
        this.hmpSOConexiones = new HashMap<>();
        this.hmpColumnasTablas = new HashMap<>();
        this.hmpColumnasSelect = new HashMap<>();
    }

    public static ObjectsApplication getInstance() {
        return ObjectsApplicationHolder.INSTANCE;
    }

    private static class ObjectsApplicationHolder {

        private static final ObjectsApplication INSTANCE = new ObjectsApplication();
    }

    public HashMap<String, Object> getHmpSOConexiones() {
        return this.hmpSOConexiones;
    }
    public void setHmpSOConexiones(HashMap<String, Object> hmpSOConexiones) {
        this.hmpSOConexiones = hmpSOConexiones;
    }
    public void AgregarColumnas(String sTabla, String sPrefijo, LinkedList<LinkedHashMap<String, Object>> llsLhmColumnas) {
        StringBuilder sblColumnasSelect = new StringBuilder();
        llsLhmColumnas.forEach(llsLhmColumna -> sblColumnasSelect.append(", ").append(sPrefijo).append(".").append(llsLhmColumna.get("COLUMN_NAME")));

        this.hmpColumnasSelect.put(sTabla, sblColumnasSelect.substring(2));
        this.hmpColumnasTablas.put(sTabla, llsLhmColumnas);
    }
}
