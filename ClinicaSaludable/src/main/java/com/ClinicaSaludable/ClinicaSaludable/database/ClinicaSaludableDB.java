package com.ClinicaSaludable.ClinicaSaludable.database;

import com.gerleinco.databasemanager.ConnectManagerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * La clase `ArasDB` extiende `ConnectManagerImpl` y está encargada de establecer una conexión a la base de datos utilizando
 * la información de configuración contenida en un archivo XML.
 */
public class ClinicaSaludableDB  extends ConnectManagerImpl {

    // Nombre del archivo de configuración XML.
    private static final String CONFIG_FILE = "ClinicaSaludableDB.xml";

    /**
     * Constructor de la clase `ArasDB`. Llama al constructor de la clase base `ConnectManagerImpl` con los parámetros
     * necesarios (usuario, contraseña, URL de conexión) obtenidos del archivo de configuración XML.
     */
    public ClinicaSaludableDB() {
//        super(getUser(), getPassword(), getUrl());    //PARA LOCAL
        super("root", "ClinicaSaludableIS2", "jdbc:mysql://clinicasaludable.cd8mg6qw2k00.us-east-2.rds.amazonaws.com:3306/clinica_saludable");          //PARA NUBE
    }

    /**
     * Carga las propiedades desde el archivo XML de configuración.
     *
     * @return Un objeto `Properties` que contiene las propiedades del archivo XML.
     * @throws RuntimeException si el archivo no existe o si ocurre un error al leerlo.
     */

    private static Properties loadProperties() {
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);

        // Verifica si el archivo de configuración existe en la raíz del proyecto
        if (!file.exists()) {
            throw new RuntimeException(" Error: El archivo " + CONFIG_FILE + " no existe en la raíz del proyecto.");
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            // Carga las propiedades desde el archivo XML
            properties.loadFromXML(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo XML: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el nombre de usuario de la base de datos desde el archivo de configuración.
     *
     * @return El nombre de usuario para la conexión a la base de datos.
     */
    private static String getUser() {
        return loadProperties().getProperty("USUARIO");
    }

    /**
     * Obtiene la contraseña de la base de datos desde el archivo de configuración.
     *
     * @return La contraseña para la conexión a la base de datos. (Nota: el archivo XML podría usar una clave diferente)
     */
    private static String getPassword() {
        return loadProperties().getProperty("RUTA"); // Verifica que este sea realmente el password
    }

    /**
     * Obtiene la URL de conexión a la base de datos desde el archivo de configuración.
     *
     * @return La URL de conexión a la base de datos.
     */
    private static String getUrl() {
        return loadProperties().getProperty("CONEXION");
    }

}
