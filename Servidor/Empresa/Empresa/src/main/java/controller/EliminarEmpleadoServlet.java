package controller;

import dao.EmpleadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet encargado de eliminar un empleado de la base de datos.
 *
 * Recibe el DNI del empleado, comprueba que sea válido y que el empleado
 * exista antes de solicitar su eliminación.
 *
 * @version 1.0
 */
@WebServlet("/empleados/eliminar")
public class EliminarEmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Procesa la solicitud de eliminación de un empleado.
     *
     * Comprueba el DNI recibido, busca al empleado y, si existe,
     * solicita su eliminación mediante EmpleadoDAO.
     *
     * @param request petición HTTP que contiene el DNI del empleado.
     * @param response respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error al eliminar el empleado.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Configura la codificación para procesar correctamente caracteres especiales.
        request.setCharacterEncoding("UTF-8");

        // Obtiene el DNI del empleado que se quiere eliminar.
        String dni = request.getParameter("dni");

        // Comprueba que el DNI tenga ocho números y una letra.
        if (dni == null || !dni.trim().matches("\\d{8}[A-Za-z]")) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El DNI indicado no es válido."
            );
            return;
        }

        // Elimina espacios y convierte la letra del DNI a mayúscula.
        dni = dni.trim().toUpperCase();

        try {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            // Comprueba que el empleado exista antes de eliminarlo.
            if (empleadoDAO.obtenerEmpleadoPorDni(dni) == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "No se ha encontrado el empleado."
                );
                return;
            }

            // Solicita al DAO la eliminación del empleado.
            empleadoDAO.eliminarEmpleado(dni);

            // Regresa al listado indicando que la eliminación se ha realizado.
            response.sendRedirect(
                    request.getContextPath() + "/empleados?eliminado=true"
            );

        } catch (SQLException e) {
            // Comunica al contenedor del servlet el error producido en la base de datos.
            throw new ServletException(
                    "No se pudo eliminar el empleado.",
                    e
            );
        }
    }
}