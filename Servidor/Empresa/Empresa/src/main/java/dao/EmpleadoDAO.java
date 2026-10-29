
package dao;

import conexion.ConexionDB;
import model.Empleado;
import model.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> obtenerEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT e.nombre, e.dni, e.sexo, e.categoria, " +
                "e.anyos_trabajados, n.sueldo " +
                "FROM empleados e " +
                "INNER JOIN nominas n ON e.dni = n.dni " +
                "ORDER BY e.nombre";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Empleado empleado = leerEmpleado(resultado);
                empleados.add(empleado);
            }
        }

        return empleados;
    }

    public Empleado obtenerEmpleadoPorDni(String dni) throws SQLException {
        String sql = "SELECT e.nombre, e.dni, e.sexo, e.categoria, " +
                "e.anyos_trabajados, n.sueldo " +
                "FROM empleados e " +
                "LEFT JOIN nominas n ON e.dni = n.dni " +
                "WHERE e.dni = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, dni);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return leerEmpleado(resultado);
                }
            }
        }

        return null;
    }

    public void crearEmpleado(Empleado empleado) throws SQLException {
        String sqlEmpleado =
                "INSERT INTO empleados " +
                        "(dni, nombre, sexo, categoria, anyos_trabajados) " +
                        "VALUES (?, ?, ?, ?, ?)";

        String sqlNomina =
                "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";

        int sueldo = Nomina.sueldo(empleado);

        try (Connection conexion = ConexionDB.conectar()) {
            conexion.setAutoCommit(false);

            try {
                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlEmpleado)) {

                    sentencia.setString(1, empleado.getDni());
                    sentencia.setString(2, empleado.getNombre());
                    sentencia.setString(3, String.valueOf(empleado.getSexo()));
                    sentencia.setInt(4, empleado.getCategoria());
                    sentencia.setInt(5, empleado.getAnyosTrabajados());

                    sentencia.executeUpdate();
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlNomina)) {

                    sentencia.setString(1, empleado.getDni());
                    sentencia.setInt(2, sueldo);

                    sentencia.executeUpdate();
                }

                conexion.commit();

            } catch (SQLException e) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }
                throw e;
            }
        }
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        String sqlEmpleado =
                "UPDATE empleados SET nombre = ?, sexo = ?, " +
                        "categoria = ?, anyos_trabajados = ? WHERE dni = ?";

        String sqlNomina =
                "UPDATE nominas SET sueldo = ? WHERE dni = ?";

        int sueldo = Nomina.sueldo(empleado);

        try (Connection conexion = ConexionDB.conectar()) {
            conexion.setAutoCommit(false);

            try {
                int filasActualizadas;

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlEmpleado)) {

                    sentencia.setString(1, empleado.getNombre());
                    sentencia.setString(2, String.valueOf(empleado.getSexo()));
                    sentencia.setInt(3, empleado.getCategoria());
                    sentencia.setInt(4, empleado.getAnyosTrabajados());
                    sentencia.setString(5, empleado.getDni());

                    filasActualizadas = sentencia.executeUpdate();
                }

                if (filasActualizadas == 0) {
                    throw new SQLException("El empleado no existe.");
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlNomina)) {

                    sentencia.setInt(1, sueldo);
                    sentencia.setString(2, empleado.getDni());

                    int nominasActualizadas = sentencia.executeUpdate();

                    if (nominasActualizadas == 0) {
                        throw new SQLException(
                                "No existe una nómina para este empleado.");
                    }
                }

                conexion.commit();

            } catch (SQLException e) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }
                throw e;
            }
        }
    }

    public void eliminarEmpleado(String dni) throws SQLException {
        String sqlNomina = "DELETE FROM nominas WHERE dni = ?";
        String sqlEmpleado = "DELETE FROM empleados WHERE dni = ?";

        try (Connection conexion = ConexionDB.conectar()) {
            conexion.setAutoCommit(false);

            try {
                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlNomina)) {

                    sentencia.setString(1, dni);
                    sentencia.executeUpdate();
                }

                try (PreparedStatement sentencia =
                             conexion.prepareStatement(sqlEmpleado)) {

                    sentencia.setString(1, dni);

                    int filasEliminadas = sentencia.executeUpdate();

                    if (filasEliminadas == 0) {
                        throw new SQLException("El empleado no existe.");
                    }
                }

                conexion.commit();

            } catch (SQLException e) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }
                throw e;
            }
        }
    }

    private Empleado leerEmpleado(ResultSet resultado) throws SQLException {
        Empleado empleado = new Empleado(
                resultado.getString("nombre"),
                resultado.getString("dni"),
                resultado.getString("sexo").charAt(0),
                resultado.getInt("categoria"),
                resultado.getInt("anyos_trabajados")
        );

        empleado.setSueldo(resultado.getInt("sueldo"));

        return empleado;
    }

    /**
     * Busca empleados utilizando uno o varios criterios.
     * Los campos vacíos no se tienen en cuenta.
     *
     * @param nombre nombre o parte del nombre
     * @param dni DNI completo o parcial
     * @param sexo sexo del empleado
     * @param categoria categoría o null
     * @param anyosTrabajados años trabajados o null
     * @return lista de empleados encontrados
     * @throws SQLException si ocurre un error en la base de datos
     */
    public List<Empleado> buscarEmpleados(
            String nombre,
            String dni,
            String sexo,
            Integer categoria,
            Integer anyosTrabajados) throws SQLException {

        List<Empleado> empleados = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT e.nombre, e.dni, e.sexo, e.categoria, " +
                        "e.anyos_trabajados, n.sueldo " +
                        "FROM empleados e " +
                        "LEFT JOIN nominas n ON e.dni = n.dni " +
                        "WHERE 1 = 1"
        );

        if (nombre != null && !nombre.isBlank()) {
            sql.append(" AND e.nombre LIKE ?");
        }

        if (dni != null && !dni.isBlank()) {
            sql.append(" AND e.dni LIKE ?");
        }

        if (sexo != null && !sexo.isBlank()) {
            sql.append(" AND e.sexo = ?");
        }

        if (categoria != null) {
            sql.append(" AND e.categoria = ?");
        }

        if (anyosTrabajados != null) {
            sql.append(" AND e.anyos_trabajados = ?");
        }

        sql.append(" ORDER BY e.nombre ASC");

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql.toString())) {

            int posicion = 1;

            if (nombre != null && !nombre.isBlank()) {
                sentencia.setString(
                        posicion++,
                        "%" + nombre.trim() + "%"
                );
            }

            if (dni != null && !dni.isBlank()) {
                sentencia.setString(
                        posicion++,
                        "%" + dni.trim() + "%"
                );
            }

            if (sexo != null && !sexo.isBlank()) {
                sentencia.setString(posicion++, sexo);
            }

            if (categoria != null) {
                sentencia.setInt(posicion++, categoria);
            }

            if (anyosTrabajados != null) {
                sentencia.setInt(posicion++, anyosTrabajados);
            }

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    empleados.add(leerEmpleado(resultado));
                }
            }
        }

        return empleados;
    }
}
