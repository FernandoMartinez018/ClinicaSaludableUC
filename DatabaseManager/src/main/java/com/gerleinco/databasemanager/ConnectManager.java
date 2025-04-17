package com.gerleinco.databasemanager;

import java.sql.Connection;

public interface ConnectManager {

    void closeConnection();

    Connection getCnnApp();


}


