package com.gerleinco.databasemanager;

import com.gerleinco.databasemanager.exceptions.DatabaseQueryException;
import com.gerleinco.databasemanager.util.ObjectsApplication;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


public class QueryManager {



    private final ConnectManager estbConexion;
    public QueryManager(ConnectManager estbConexion) {
        this.estbConexion = estbConexion;
    }

    public LinkedHashMap<String, Object> OperacionBD(LinkedHashMap<String, Object> lhmpEntidad, String sAccion) {
        return OperacionBD(lhmpEntidad, sAccion, null);
    }


    public LinkedHashMap<String, Object> OperacionBD(LinkedHashMap<String, Object> lhmpEntidad, String sAccion, LinkedHashMap<String, Object> hmpSalida) {
        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String sSQLPuro = "";
        String sWherePuro = "";
        try {
            String sSQL = "";
            Object[] aoVals = new Object[1024];
            Object[] aoValsWh = new Object[1024];
            int i = 0;
            int iWh = 0;
            if (lhmpEntidad.containsKey("INICIO_SQL")) {
                sSQL += lhmpEntidad.get("INICIO_SQL");
            }
            String sTABLABD;
            sTABLABD = lhmpEntidad.get("TABLABD").toString();
            if (sAccion.equalsIgnoreCase("INSERTAR")) {
                sSQL += "INSERT INTO " + lhmpEntidad.get("TABLABD") + "(";
                String sVals = "";
                for (Iterator<Entry<String, Object>> it = lhmpEntidad.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<String, Object> meObj;
                    meObj = it.next();
                    String sLlave = meObj.getKey();
                    if (sLlave.equalsIgnoreCase("CODREG") || sLlave.equalsIgnoreCase("INICIO_SQL") || sLlave.equalsIgnoreCase("FIN_SQL")) {
                        continue;
                    }
//                    System.out.println("Driver JDBC utilizado: " + conn.getMetaData().getDriverName());
//                    System.out.println("Versión del driver: " + conn.getMetaData().getDriverVersion());
//                    System.out.println("URL de conexión: " + conn.getMetaData().getURL());
//                    System.out.println("Usuario conectado: " + conn.getMetaData().getUserName());
//                    if (!VerificarColumnaTabla(sTABLABD, sLlave)) {
//                        continue;
//                    }
                    if (!sLlave.equalsIgnoreCase("TABLABD")) {
                        Object oVal = meObj.getValue();
                        if (oVal != null) {
                            sSQL += sLlave + ", ";
                            String sClase = oVal.getClass().getCanonicalName().toUpperCase();
//                            if ((sClase.contains("DATE") || sClase.contains("TIME")) && !VerificarColumnaTabla(sTABLABD, sLlave)){
//                                sVals += "TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS'), ";
//                                aoVals[i] = sdfFecha.format(oVal);
//                                i++;
//                            } else {
                                if (oVal.toString().indexOf("XDIRECTOX") == 0) {
                                    sVals += oVal.toString().replace("XDIRECTOX", "") + ", ";
                                } else if (oVal.toString().indexOf("SYSDATE") == 0) {
                                    sVals += "CURRENT_TIMESTAMP, ";
                                } else {
                                    sVals += "?, ";
                                    aoVals[i] = oVal;
                                    i++;
                                }
//                            }
                        }
                    }
                }
                sSQL = sSQL.substring(0, sSQL.length() - 2);
                sSQL += ")VALUES(" + sVals.substring(0, sVals.length() - 2) + ")";

            }
            if (sAccion.equalsIgnoreCase("ACTUALIZAR")) {
                sSQL += "UPDATE " + lhmpEntidad.get("TABLABD") + " SET ";
                sSQLPuro = "UPDATE " + lhmpEntidad.get("TABLABD") + " SET ";
                String sWhere = "";
                for (Iterator<Entry<String, Object>> it = lhmpEntidad.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<String, Object> meObj;
                    meObj = it.next();
                    String sLlave = meObj.getKey();
                    if (sLlave.equalsIgnoreCase("CODREG") || sLlave.equalsIgnoreCase("INICIO_SQL") || sLlave.equalsIgnoreCase("FIN_SQL")) {
                        continue;
                    }
                    if (!sLlave.equalsIgnoreCase("TABLABD")) {
                        if (!sLlave.equalsIgnoreCase("WHEREBD")) {
//                            if (!VerificarColumnaTabla(sTABLABD, sLlave)) {
//                                continue;
//                            }

                            Object oVal = meObj.getValue();
                            if (oVal != null) {
                                sSQL += sLlave + " = ";
                                sSQLPuro += sLlave + " = ";

                                String sClase = oVal.getClass().getCanonicalName().toUpperCase();

//                                if ((sClase.contains("DATE") || sClase.contains("TIME")) && !VerificarColumnaTabla(sTABLABD, sLlave)) {
//                                    sSQL += "TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')";
//                                    aoVals[i] = sdfFecha.format(oVal);
//                                    i++;
//                                } else {
                                    Object oValOp = oVal;
                                    if (oVal.toString().indexOf("XDIRECTOX") == 0) {
                                        sSQL += oVal.toString().replace("XDIRECTOX", "");
                                    } else if (oVal.toString().indexOf("SYSDATE") == 0) {
                                        sSQL += "CURRENT_TIMESTAMP";
                                    } else {
                                        sSQL += "?";
                                        aoVals[i] = oValOp;
                                        sSQLPuro += aoVals[i];
                                        i++;
                                    }
//                                }
                                sSQL += ", ";
                                sSQLPuro += "::" + sClase + ", \n";
                            }/*else{
                                //sSQL += sLlave + " = NULL, ";
                            }*/
                        } else {
                            LinkedHashMap<String, Object> lhmpWhere;
                            lhmpWhere = (LinkedHashMap<String, Object>) meObj.getValue();
                            for (Iterator<Entry<String, Object>> itW = lhmpWhere.entrySet().iterator(); itW.hasNext();) {

                                Map.Entry<String, Object> meObjW;
                                meObjW = itW.next();

                                sLlave = meObjW.getKey();
                                sWhere += (sWhere.trim().length() == 0 ? " WHERE" : "AND") + " ";
                                sWherePuro += (sWherePuro.trim().length() == 0 ? " WHERE" : "AND") + " ";
                                if (sLlave.equalsIgnoreCase("SQL")) {
                                    sWhere += meObjW.getValue();
                                } else {
                                    sWhere += sLlave;
                                    sWherePuro += sLlave;

                                    Object oVal = meObjW.getValue();
                                    if (oVal != null) {
                                        String sClase = oVal.getClass().getCanonicalName().toUpperCase();
//                                        if ((sClase.contains("DATE") || sClase.contains("TIME")) && !VerificarColumnaTabla(sTABLABD, sLlave)) {
//                                            sWhere += " = TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') ";
//                                            aoValsWh[iWh] = sdfFecha.format(oVal);
//                                            sWherePuro += " = TO_DATE(" + aoVals[i] + ", 'DD/MM/YYYY HH24:MI:SS') ::" + sClase;
//                                            iWh++;
//                                        } else {
                                            if (oVal.toString().indexOf("XDIRECTOX") == 0) {
                                                sWhere += " = " + oVal.toString().replace("XDIRECTOX", "");
                                            } else if (oVal.toString().indexOf("SYSDATE") == 0) {
                                                sWhere += " = CURRENT_TIMESTAMP ";
                                            } else {
                                                sWhere += " = ? ";
                                                aoValsWh[iWh] = oVal;
                                                iWh++;
                                            }
                                            sWherePuro += " = " + aoVals[i] + "::" + sClase;
//                                        }
                                    } else {
                                        sWhere += " is null ";
                                        sWherePuro += " is null ";
                                    }
                                }
                            }
                        }
                    }
                }
                if (sWhere.trim().length() == 0) {
                    throw new Exception("NO PUEDE ACTUALIZAR TODA LA TABLA, DEBE INGRESAR ALGUN CRITERIO");
                }
                sSQL = sSQL.substring(0, sSQL.length() - 2) + sWhere;
                sSQLPuro = sSQLPuro.substring(0, sSQLPuro.length() - 2) + sWherePuro;
            }
            if (sAccion.equalsIgnoreCase("ELIMINAR")) {
                sSQL += "DELETE FROM " + lhmpEntidad.get("TABLABD") + " ";
                String sWhere = "";
                LinkedHashMap<String, Object> lhmpWhere;
                lhmpWhere = (LinkedHashMap<String, Object>) lhmpEntidad.get("WHEREBD");
                for (Iterator<Entry<String, Object>> itW = lhmpWhere.entrySet().iterator(); itW.hasNext();) {
                    Map.Entry<String, Object> meObjW;
                    meObjW = itW.next();
                    String sLlave = meObjW.getKey();
                    if (sLlave.equalsIgnoreCase("CODREG") || sLlave.equalsIgnoreCase("INICIO_SQL") || sLlave.equalsIgnoreCase("FIN_SQL")) {
                        continue;
                    }
                    sWhere += (sWhere.trim().length() == 0 ? " WHERE" : "AND");

                    if (sLlave.equalsIgnoreCase("SQL")) {
                        sWhere += " " + meObjW.getValue();
                    } else {
                        sWhere += " " + sLlave;

                        Object oVal = meObjW.getValue();
                        if (oVal != null) {
                            String sClase = oVal.getClass().getCanonicalName().toUpperCase();
//                            if ((sClase.contains("DATE") || sClase.contains("TIME")) && !VerificarColumnaTabla(sTABLABD, sLlave)) {
//                                sWhere += " = TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') ";
//                                aoVals[i] = sdfFecha.format(oVal);
//                                i++;
//                            } else {
                                Object oValOp = oVal;
                                if (oVal.toString().indexOf("XDIRECTOX") == 0) {
                                    sWhere += " = " + oVal.toString().replace("XDIRECTOX", "");
                                } else if (oVal.toString().indexOf("SYSDATE") == 0) {
                                    sWhere += " = CURRENT_TIMESTAMP ";
                                } else {
                                    sWhere += " = ? ";
                                    aoVals[i] = oValOp;
                                    i++;
                                }
//                            }
                        } else {
                            sWhere += " is null ";
                        }
                    }
                }
                if (sWhere.trim().length() == 0) {
                    throw new Exception("NO PUEDE ELIMINAR TODA LA TABLA, DEBE INGRESAR ALGUN CRITERIO");
                }
                sSQL += sWhere;
            }
            if (lhmpEntidad.containsKey("FIN_SQL")) {
                sSQL += lhmpEntidad.get("FIN_SQL");
            }

        //    System.out.println("sSQL = " + sSQL);
        //    System.out.println("entidad = " + lhmpEntidad);
            LinkedHashMap<Integer, Object> hmpDatos;
            hmpDatos = new LinkedHashMap<>();
            for (int j = 0; j < i; j++) {
                hmpDatos.put(j + 1, aoVals[j]);

            }
            for (int j = 0; j < iWh; j++) {
                hmpDatos.put(j + i + 1, aoValsWh[j]);

            }

            String sRet;
            sRet = "";
            if (hmpSalida == null) {
                sRet = Actualizar(sSQL, hmpDatos);
                String sCommit = Actualizar("commit", null);

            } else {
                LinkedHashMap<String, Object> hmpRet = this.EjecutarRetorno(sSQL, hmpDatos, hmpSalida);
                for (Iterator<Map.Entry<String, Object>> it = hmpRet.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<String, Object> meObj;
                    meObj = it.next();
                    lhmpEntidad.put(meObj.getKey(), meObj.getValue());
                }
            }
            if (sRet.contains("ERROR")) {

                lhmpEntidad.put("ERROR", sRet);
            }
            lhmpEntidad.put("REGISTROS", sRet);
        } catch (Exception e) {

            lhmpEntidad.put("ERROR_MAIN", "Ocurrio un error procesando su solicitud. Por favor comuniquese con el Administrador del sistema");
            lhmpEntidad.put("ERROR",e);
            throw new DatabaseQueryException("Error al cerrar ResultSet", e);
        }
        return lhmpEntidad;
    }

    /**
     * @param sSQL
     * @param hmpCriterios
     * @return
     */
    public String Actualizar(String sSQL, LinkedHashMap<Integer, Object> hmpCriterios) {

        DecimalFormat dfrNumero = new DecimalFormat("####################0.00#");
        String sRetorno;
        sRetorno = "";
        PreparedStatement pstDatos;
        pstDatos = null;
        int iIntentos = 0;
        while (iIntentos < 1) {
            try {

                pstDatos = estbConexion.getCnnApp().prepareStatement(sSQL);
                if (hmpCriterios != null) {

                    for (Iterator<Entry<Integer, Object>> it = hmpCriterios.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<Integer, Object> meObj;
                        meObj = it.next();
                        if (meObj.getValue() == null) {

                            throw new Exception("ERROR: FALTA INGRESAR UN DATO PARA LA ACTUALIZACION EN BASE DE DATOS");
                        }

                        Object oVal = meObj.getValue();
                        if (oVal != null) {
                            String sClase;
                            sClase = oVal.getClass().getCanonicalName().toUpperCase();
                            if (sClase.indexOf("DOUBLE") > 0) {
                                oVal = dfrNumero.format(oVal);
                            }
                            if (sClase.indexOf("STRING") > 0) {
                                oVal = oVal.toString().trim();
                            }
                        }
                        pstDatos.setObject(meObj.getKey(), oVal);
                    }
                }
                int iRegs = pstDatos.executeUpdate();
                sRetorno = "" + iRegs;
                break;
            } catch (Exception eError) {

              //  sRetorno = "ERROR: Ocurrio un error procesando su solicitud. Por favor comuniquese con el Administrador del sistema";
                  sRetorno = "ERROR: "+eError.toString();
                try {
                    Thread.sleep(500);
                } catch (Exception eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
            } finally {
                try {
                    if (pstDatos != null) {
                        pstDatos.close();
                    }
                } catch (SQLException eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
                try {
                    pstDatos = null;
                } catch (Exception eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
            }
            iIntentos++;
        }
        return sRetorno;
    }
    public String EjecutarProcedimientos(String sSQL, LinkedHashMap<Integer, Object> hmpCriterios) {

        DecimalFormat dfrNumero = new DecimalFormat("####################0.00#");
        String sRetorno;
        sRetorno = "";
        PreparedStatement pstDatos;
        pstDatos = null;
        int iIntentos = 0;
        while (iIntentos < 1) {
            try {

                pstDatos = estbConexion.getCnnApp().prepareStatement(sSQL);
                if (hmpCriterios != null) {

                    for (Iterator<Entry<Integer, Object>> it = hmpCriterios.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<Integer, Object> meObj;
                        meObj = it.next();

                        Object oVal = meObj.getValue();

                        if (oVal != null) {
                            String sClase;
                            sClase = oVal.getClass().getCanonicalName().toUpperCase();
                            if (sClase.indexOf("DOUBLE") > 0) {
                                oVal = dfrNumero.format(oVal);
                            }
                            if (sClase.indexOf("STRING") > 0) {
                                oVal = oVal.toString().trim();
                            }
                        }else{
                            oVal = null;
                        }
                        pstDatos.setObject(meObj.getKey(), oVal);
                    }
                }
                int iRegs = pstDatos.executeUpdate();
                sRetorno = "" + iRegs;
                break;
            } catch (Exception eError) {

                sRetorno = "ERROR: Ocurrio un error procesando su solicitud. Por favor comuniquese con el Administrador del sistema";

                try {
                    Thread.sleep(500);
                } catch (Exception eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
            } finally {
                try {
                    if (pstDatos != null) {
                        pstDatos.close();
                    }
                } catch (SQLException eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
                try {
                    pstDatos = null;
                } catch (Exception eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
            }
            iIntentos++;
        }
        return sRetorno;
    }

    /**
     * @param sSQL
     * @param hmpCriterios
     * @param hmpSalida
     * @return
     */
    public LinkedHashMap<String, Object> EjecutarRetorno(String sSQL, LinkedHashMap<Integer, Object> hmpCriterios, LinkedHashMap<String, Object> hmpSalida) {
        return EjecutarRetorno(sSQL, hmpCriterios, hmpSalida, false);
    }

    public LinkedHashMap<String, Object> EjecutarRetorno(String sSQL, LinkedHashMap<Integer, Object> hmpCriterios, LinkedHashMap<String, Object> hmpSalida,  boolean bReintente) {

        DecimalFormat dfrNumero = new DecimalFormat("####################0.00#");
        LinkedHashMap<String, Object> hmpRet;
        hmpRet = new LinkedHashMap<>();
        CallableStatement pstDatos;
        pstDatos = null;
        int iNumIntentos = 1;

        for (int iIntentos = 1; iIntentos <= iNumIntentos; iIntentos++) {
            boolean bError = false;
            Connection cnnER = estbConexion.getCnnApp();

            try {
//
                pstDatos = cnnER.prepareCall(sSQL);
                int column = 1;
                if (hmpCriterios != null) {
                    for (Iterator<Entry<Integer, Object>> it = hmpCriterios.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<Integer, Object> meObj;
                        meObj = it.next();
                        if (meObj.getValue() == null) {

                            throw new Exception("FALTA INGRESAR UN DATO PARA LA ACTUALIZACION EN BASE DE DATOS");
                        }

                        Object oVal = meObj.getValue();
                        String sClase;
                        sClase = null;
                        if (oVal != null) {
                            sClase = oVal.getClass().getCanonicalName().toUpperCase();
                            if (sClase.indexOf("DOUBLE") > 0) {
                                oVal = dfrNumero.format(oVal);
                            }
                            if (sClase.indexOf("STRING") > 0) {
                                oVal = oVal.toString().trim();
                            }
                        }
                        if (oVal != null && sClase != null && sClase.equalsIgnoreCase("java.util.Date")) {
                            Date sdtFecha = new Date(((java.util.Date) oVal).getTime());
                            pstDatos.setDate(meObj.getKey(), sdtFecha);
                        } else {
                            pstDatos.setObject(meObj.getKey(), oVal);
                        }
                        column++;
                    }
                }
                int iColRet = column;
                if (hmpSalida != null) {
                    for (Iterator<Entry<String, Object>> it = hmpSalida.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<String, Object> meObj;
                        meObj = it.next();
                        pstDatos.registerOutParameter(column++, (int) meObj.getValue());
                    }
                }
                int iRegs = pstDatos.executeUpdate();
                hmpRet.put("REGISTROS", iRegs);
                if (hmpSalida != null) {
                    column = iColRet;
                    for (Iterator<Entry<String, Object>> it = hmpSalida.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<String, Object> meObj;
                        meObj = it.next();
                        hmpRet.put(meObj.getKey(), pstDatos.getObject(column++));
                    }
                }
            } catch (Exception eError) {
                bError = true;
                boolean bMostrarError = true;
                if (bReintente && iIntentos < iNumIntentos) {
                    bMostrarError = false;
                }

                if (bMostrarError) {

                    hmpRet.put("ERROR", eError.getLocalizedMessage());
                }
            } finally {
                try {
                    if (pstDatos != null) {
                        pstDatos.close();
                    }
                } catch (NullPointerException | SQLException eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
                try {
                    pstDatos = null;
                    if (pstDatos == null) {

                    }
                } catch (Exception eLoc) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
                }
            }
            if (!bError) {
                break;
            } else if (bReintente && iIntentos < iNumIntentos) {

                try {
                    cnnER.close();
                } catch (SQLException eError) {
                    throw new DatabaseQueryException("Error al cerrar ResultSet", eError);
                }
                cnnER = null;
            }
        }
        return hmpRet;
    }

    public LinkedList<LinkedHashMap<String, Object>> Consultar(LinkedHashMap<String, Object> lhmCriterios) {
        LinkedList<LinkedHashMap<String, Object>> llsLhmRet;
        llsLhmRet = new LinkedList<>();
        PreparedStatement pstDatos;
        ResultSet rstDatos;
        String sSQL;

        pstDatos = null;
        rstDatos = null;
        sSQL = "";
        try {
            sSQL = "SELECT " + lhmCriterios.get("CAMPOSBD");
            sSQL += " FROM " + lhmCriterios.get("TABLASBD");

            if (lhmCriterios.containsKey("WHEREBD")) {
                sSQL += " WHERE " + lhmCriterios.get("WHEREBD");
            }
            if (lhmCriterios.containsKey("QUERYADDBD")) {
                sSQL += lhmCriterios.get("QUERYADDBD");
            }

            if (lhmCriterios.containsKey("GROUPBY")) {
                sSQL += " GROUP BY " + lhmCriterios.get("GROUPBY");
            }

            if (lhmCriterios.containsKey("ORDERBY")) {
                sSQL += " ORDER BY " + lhmCriterios.get("ORDERBY");
            }

         //   System.out.println("datos del query: " + sSQL);

            pstDatos = estbConexion.getCnnApp().prepareStatement(sSQL);

            LinkedHashMap<Integer, Object> lhmpWhere;
            lhmpWhere = (LinkedHashMap<Integer, Object>) lhmCriterios.get("WHEREBD_HM");


            if (lhmpWhere != null) {

                for (Iterator<Entry<Integer, Object>> itW = lhmpWhere.entrySet().iterator(); itW.hasNext();) {
                    Map.Entry<Integer, Object> meObjW;
                    meObjW = itW.next();

                    if (meObjW.getValue() != null) {
                        System.out.println("true");
                        if (meObjW.getValue().getClass().getCanonicalName().equalsIgnoreCase("java.util.Date")) {
                            Date sdtFecha = new Date(((java.util.Date) meObjW.getValue()).getTime());
                            pstDatos.setDate(meObjW.getKey(), sdtFecha);
                        } else {
                            pstDatos.setObject(meObjW.getKey(), meObjW.getValue());
                        }

                    } else {
                        System.out.println("false");
                        pstDatos.setObject(meObjW.getKey(), null);
                    }
                }
            }

            int iCols = 0;
            int iRegs = 0;
            String[] asCols;
            asCols = new String[0];

            rstDatos = pstDatos.executeQuery();
            while (rstDatos.next()) {
                if (iRegs == 0) {

                    ResultSetMetaData rsmdDatos = rstDatos.getMetaData();
                    iCols = rsmdDatos.getColumnCount();
                    asCols = new String[iCols];
                    for (int i = 0; i < iCols; i++) {
                        asCols[i] = rsmdDatos.getColumnName(i + 1);
                    }
                }
                LinkedHashMap<String, Object> hmpCampos = new LinkedHashMap<>();
                hmpCampos.put("codreg", iRegs + 1);

                for (int i = 0; i < iCols; i++) {
                    Object oVal;
                    oVal = rstDatos.getObject(asCols[i]);
                    if (oVal != null && oVal.getClass().getCanonicalName().toUpperCase().contains("BLOB")) {
                        hmpCampos.put(asCols[i], rstDatos.getBytes(asCols[i]));
                    } else {
                        hmpCampos.put(asCols[i], oVal);
                    }
                }
                llsLhmRet.add(hmpCampos);
                iRegs++;
            }

        } catch (Exception eError) {

            LinkedHashMap<String, Object> lhmError = new LinkedHashMap<>();

            lhmError.put("ERROR", eError.getLocalizedMessage());
            throw new DatabaseQueryException("Error al cerrar ResultSet", eError);
        }  finally {
            try {
                if (rstDatos != null) {
                    rstDatos.close();
                }
            } catch (Exception eLoc) {
                throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
            }

            try {
                if (pstDatos != null) {
                    pstDatos.close();
                }
            } catch (Exception eLoc) {
                throw new DatabaseQueryException("Error al cerrar PreparedStatement", eLoc);
            }
    }

        return llsLhmRet;
    }

    public LinkedList<LinkedHashMap<String, Object>> ConsultarDistinct(LinkedHashMap<String, Object> lhmCriterios) {
        LinkedList<LinkedHashMap<String, Object>> llsLhmRet;
        llsLhmRet = new LinkedList<>();
        PreparedStatement pstDatos;
        ResultSet rstDatos;
        String sSQL;

        pstDatos = null;
        rstDatos = null;
        sSQL = "";
        try {
            sSQL = "SELECT DISTINCT " + lhmCriterios.get("CAMPOSBD");
            sSQL += " FROM " + lhmCriterios.get("TABLASBD");
            if (lhmCriterios.containsKey("WHEREBD")) {
                sSQL += " WHERE " + lhmCriterios.get("WHEREBD");
            }
            if (lhmCriterios.containsKey("QUERYADDBD")) {
                sSQL += lhmCriterios.get("QUERYADDBD");
            }
            if (lhmCriterios.containsKey("ORDERBY")) {
                sSQL += " ORDER BY " + lhmCriterios.get("ORDERBY");
            }


            pstDatos = estbConexion.getCnnApp().prepareStatement(sSQL);

            LinkedHashMap<Integer, Object> lhmpWhere;
            lhmpWhere = (LinkedHashMap<Integer, Object>) lhmCriterios.get("WHEREBD_HM");
            if (lhmpWhere != null) {
                for (Iterator<Entry<Integer, Object>> itW = lhmpWhere.entrySet().iterator(); itW.hasNext();) {
                    Map.Entry<Integer, Object> meObjW;
                    meObjW = itW.next();
                    if (meObjW.getValue() != null) {
                        if (meObjW.getValue().getClass().getCanonicalName().equalsIgnoreCase("java.util.Date")) {
                            Date sdtFecha = new Date(((java.util.Date) meObjW.getValue()).getTime());
                            pstDatos.setDate(meObjW.getKey(), sdtFecha);
                        } else {
                            pstDatos.setObject(meObjW.getKey(), meObjW.getValue());
                        }
                    } else {
                        pstDatos.setObject(meObjW.getKey(), null);
                    }
                }
            }

            int iCols = 0;
            int iRegs = 0;
            String[] asCols;
            asCols = new String[0];

            rstDatos = pstDatos.executeQuery();
            while (rstDatos.next()) {
                if (iRegs == 0) {

                    ResultSetMetaData rsmdDatos = rstDatos.getMetaData();
                    iCols = rsmdDatos.getColumnCount();
                    asCols = new String[iCols];
                    for (int i = 0; i < iCols; i++) {
                        asCols[i] = rsmdDatos.getColumnName(i + 1);
                    }
                }
                LinkedHashMap<String, Object> hmpCampos = new LinkedHashMap<>();
                hmpCampos.put("codreg", iRegs + 1);

                for (int i = 0; i < iCols; i++) {
                    Object oVal;
                    oVal = rstDatos.getObject(asCols[i]);
                    if (oVal != null && oVal.getClass().getCanonicalName().toUpperCase().contains("BLOB")) {
                        hmpCampos.put(asCols[i], rstDatos.getBytes(asCols[i]));
                    } else {
                        hmpCampos.put(asCols[i], oVal);
                    }
                }
                llsLhmRet.add(hmpCampos);
                iRegs++;
            }

        } catch (Exception eError) {


            LinkedHashMap<String, Object> lhmError = new LinkedHashMap<>();
            lhmError.put("ERROR", eError.getLocalizedMessage());

        } finally {
            try {
                if (rstDatos != null) {
                    rstDatos.close();
                }
            } catch (Exception eLoc) {
                throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
            }
            try {
                rstDatos = null;
                if (rstDatos == null) {

                }
            } catch (Exception eLoc) {

                throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);


            }
            try {
                if (pstDatos != null) {
                    pstDatos.close();
                }
            } catch (Exception eLoc) {
                throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
            }
            try {
                pstDatos = null;
                if (pstDatos == null) {

                }
            } catch (Exception eLoc) {
                throw new DatabaseQueryException("Error al cerrar ResultSet", eLoc);
            }
        }
        return llsLhmRet;
    }

}
