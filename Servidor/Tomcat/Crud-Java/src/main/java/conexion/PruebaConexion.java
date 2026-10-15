
package conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {

        try {
            Connection conexion = ConexionDB.conectar();

            System.out.println("¡Conexión realizada correctamente!");

            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al conectar con MariaDB:");
            e.printStackTrace();
        }
    }
}
