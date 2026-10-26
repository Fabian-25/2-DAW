package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos de nóminas.
 */
public class ConexionDB {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/nomina";

    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";

    /**
     * Establece una conexión con MariaDB.
     *
     * @return conexión abierta con la base de datos
     * @throws SQLException si se produce un error al conectar
     */

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encuentra el controlador JDBC de MariaDB", e);
        }

        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

}
