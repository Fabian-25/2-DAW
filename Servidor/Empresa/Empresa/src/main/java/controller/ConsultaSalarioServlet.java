package controller;

import dao.EmpleadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empleado;

import java.io.IOException;

/**
 * Servlet encargado de consultar el sueldo de un empleado mediante su DNI.
 *
 * Permite mostrar el formulario y procesar la búsqueda en la base de datos.
 */
@WebServlet("/consulta-salario")
public class ConsultaSalarioServlet extends HttpServlet {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Muestra el formulario para introducir el DNI del empleado.
     *
     * @param request petición HTTP
     * @param response respuesta HTTP
     * @throws ServletException si ocurre un error del servlet
     * @throws IOException si ocurre un error de entrada o salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/consulta-salario.jsp")
                .forward(request, response);
    }

    /**
     * Busca un empleado por su DNI y muestra sus datos y sueldo.
     *
     * @param request petición HTTP con el DNI introducido
     * @param response respuesta HTTP
     * @throws ServletException si ocurre un error del servlet
     * @throws IOException si ocurre un error de entrada o salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");

        // Comprobamos que se haya introducido un DNI.
        if (dni == null || dni.trim().isEmpty()) {
            request.setAttribute("mensajeError", "Debes introducir el DNI del empleado.");

            request.getRequestDispatcher("/views/consulta-salario.jsp")
                    .forward(request, response);
            return;
        }

        dni = dni.trim().toUpperCase();

        try {
            // Buscamos al empleado en la base de datos.
            Empleado empleado = empleadoDAO.obtenerEmpleadoPorDni(dni);

            if (empleado == null) {
                request.setAttribute(
                        "mensajeError",
                        "No se ha encontrado ningún empleado con ese DNI."
                );
            } else {
                // Enviamos a la JSP el empleado y su sueldo almacenado.
                request.setAttribute("empleado", empleado);
            }

        } catch (Exception e) {
            request.setAttribute(
                    "mensajeError",
                    "Ha ocurrido un error al consultar los datos. Inténtalo de nuevo."
            );
        }

        request.getRequestDispatcher("/views/consulta-salario.jsp")
                .forward(request, response);
    }
}