package nominas.laboral;

import nominas.laboral.conexionDB.ConexionDB;
import nominas.laboral.exceptions.DatosNoCorrectosException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadoDAO {

    public void insertarEmpleado(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) "
                + "VALUES (?, ?, ?, ?, ?)";

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


    public void insertarNomina(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, empleado.dni);
        sentencia.setInt(2, Nomina.sueldo(empleado));

        sentencia.executeUpdate();

        sentencia.close();
        conexion.close();
    }


    public ArrayList<Empleado> obtenerEmpleados()
            throws SQLException, DatosNoCorrectosException {

        ArrayList<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {

            String dni = resultado.getString("dni");
            String nombre = resultado.getString("nombre");
            char sexo = resultado.getString("sexo").charAt(0);
            int categoria = resultado.getInt("categoria");
            int anyos = resultado.getInt("anyos");

            Empleado empleado = new Empleado(
                    nombre,
                    dni,
                    sexo,
                    categoria,
                    anyos
            );

            empleados.add(empleado);
        }

        resultado.close();
        sentencia.close();
        conexion.close();

        return empleados;
    }


    public int obtenerSueldo(String dni) throws SQLException {

        String sql = "SELECT sueldo FROM nominas WHERE dni = ?";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, dni);

        ResultSet resultado = sentencia.executeQuery();

        int sueldo = -1;

        if (resultado.next()) {
            sueldo = resultado.getInt("sueldo");
        }

        resultado.close();
        sentencia.close();
        conexion.close();

        return sueldo;
    }

    public Empleado obtenerEmpleadoPorDni(String dni)
            throws SQLException, DatosNoCorrectosException {

        String sql = "SELECT dni, nombre, sexo, categoria, anyos " +
                "FROM empleados WHERE dni = ?";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, dni);

        ResultSet resultado = sentencia.executeQuery();

        Empleado empleado = null;

        if (resultado.next()) {

            String dniBD = resultado.getString("dni");
            String nombre = resultado.getString("nombre");
            char sexo = resultado.getString("sexo").charAt(0);
            int categoria = resultado.getInt("categoria");
            int anyos = resultado.getInt("anyos");

            empleado = new Empleado(
                    nombre,
                    dniBD,
                    sexo,
                    categoria,
                    anyos
            );
        }

        resultado.close();
        sentencia.close();
        conexion.close();

        return empleado;
    }

    public void actualizarNomina(Empleado empleado) throws SQLException {

        String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(1, Nomina.sueldo(empleado));
        sentencia.setString(2, empleado.dni);

        sentencia.executeUpdate();

        sentencia.close();
        conexion.close();
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {

        String sql = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";

        Connection conexion = ConexionDB.conectar();

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, empleado.nombre);
        sentencia.setString(2, String.valueOf(empleado.sexo));
        sentencia.setInt(3, empleado.getCategoria());
        sentencia.setInt(4, empleado.anyosTrabajados);
        sentencia.setString(5, empleado.dni);

        sentencia.executeUpdate();

        sentencia.close();
        conexion.close();
    }
}