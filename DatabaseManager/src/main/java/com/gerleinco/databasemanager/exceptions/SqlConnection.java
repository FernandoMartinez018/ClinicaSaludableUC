package com.gerleinco.databasemanager.exceptions;

public class SqlConnection extends RuntimeException {

        private final String errorCode;
        private final String sqlState;

        public SqlConnection(String mensaje, String errorCode, String sqlState) {
            super(mensaje);
            this.errorCode = errorCode;
            this.sqlState = sqlState;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getSqlState() {
            return sqlState;
        }
    }



