package nominas.laboral;

import nominas.laboral.conexionDB.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoDAO {

    public void altaEmpleado(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos_trabajados) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, empleado.dni);
        sentencia.setString(2, empleado.nombre);
        sentencia.setString(3, String.valueOf(empleado.sexo));
        sentencia.setInt(4, empleado.getCategoria());
        sentencia.setInt(5, empleado.anyosTrabajados);

        sentencia.executeUpdate();

        sentencia.close();
        conexion.close();
    }
}