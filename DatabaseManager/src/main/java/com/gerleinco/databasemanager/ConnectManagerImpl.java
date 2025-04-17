package com.gerleinco.databasemanager;

import com.gerleinco.databasemanager.exceptions.DatabaseQueryException;
import com.gerleinco.databasemanager.exceptions.SqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectManagerImpl implements ConnectManager {

    protected Connection connection;
    protected final String user;
    protected final String password;
    protected final String url;

    protected ConnectManagerImpl(String user, String password, String url)   {
        this.user = user;
        this.password = password;
        this.url = url;
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            throw new SqlConnection("Error al conectar con la base de datos",
                    String.valueOf(e.getErrorCode()),
                    e.getSQLState());
        }
        }

    @Override
    public Connection getCnnApp() {
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
