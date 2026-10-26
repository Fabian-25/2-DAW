
package controller;

import dao.EmpleadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/empleados/eliminar")
public class EliminarEmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");

        if (dni == null || !dni.trim().matches("\\d{8}[A-Za-z]")) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El DNI indicado no es válido."
            );
            return;
        }

        dni = dni.trim().toUpperCase();

        try {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            if (empleadoDAO.obtenerEmpleadoPorDni(dni) == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "No se ha encontrado el empleado."
                );
                return;
            }

            empleadoDAO.eliminarEmpleado(dni);

            response.sendRedirect(
                    request.getContextPath() + "/empleados?eliminado=true"
            );

        } catch (SQLException e) {
            throw new ServletException(
                    "No se pudo eliminar el empleado.",
                    e
            );
        }
    }
}
