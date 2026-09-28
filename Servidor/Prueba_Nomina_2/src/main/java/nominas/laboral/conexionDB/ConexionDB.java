package nominas.laboral.conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public static Connection conectar() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/nomina";
        String usuario = "root";
        String password = "123456";

        return DriverManager.getConnection(url, usuario, password);
    }
}