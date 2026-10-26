package controller;

import dao.EmpleadoDAO;
import model.Empleado;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controla las peticiones para mostrar todos los empleados.
 */
@WebServlet("/empleados")
public class ListarEmpleadosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Consulta los empleados y envía los resultados a la JSP.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try {
            List<Empleado> empleados = empleadoDAO.obtenerEmpleados();

            request.setAttribute("empleados", empleados);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/views/empleados.jsp");

            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "No se pudieron obtener los empleados de la base de datos", e);
        }
    }
}
