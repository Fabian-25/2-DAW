
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

/**
 * Servlet encargado de mostrar el formulario
 * y registrar nuevos empleados.
 */
@WebServlet("/empleados/crear")
public class CrearEmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Muestra el formulario de creación.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/views/crear-empleado.jsp");

        dispatcher.forward(request, response);
    }

    /**
     * Valida los datos y registra el empleado.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexoTexto = request.getParameter("sexo");
        String categoriaTexto = request.getParameter("categoria");
        String anyosTexto = request.getParameter("anyosTrabajados");

        // Comprobamos que se hayan recibido todos los datos.
        if (dni == null || nombre == null || sexoTexto == null
                || categoriaTexto == null || anyosTexto == null
                || dni.isBlank() || nombre.isBlank()
                || sexoTexto.isBlank() || categoriaTexto.isBlank()
                || anyosTexto.isBlank()) {

            request.setAttribute("error",
                    "Debes rellenar todos los campos.");

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
            return;
        }

        dni = dni.trim().toUpperCase();
        nombre = nombre.trim();

        // Validamos el formato básico del DNI.
        if (!dni.matches("\\d{8}[A-Z]")) {
            request.setAttribute("error",
                    "El DNI debe contener 8 números y una letra.");

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
            return;
        }

        // Validamos el sexo.
        char sexo = sexoTexto.trim().toUpperCase().charAt(0);

        if (sexo != 'M' && sexo != 'F') {
            request.setAttribute("error",
                    "El sexo debe ser M o F.");

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
            return;
        }

        int categoria;
        int anyosTrabajados;

        try {
            categoria = Integer.parseInt(categoriaTexto);
            anyosTrabajados = Integer.parseInt(anyosTexto);

        } catch (NumberFormatException e) {
            request.setAttribute("error",
                    "La categoría y los años deben ser números enteros.");

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
            return;
        }

        // Validamos los límites de la clase Empleado original.
        if (categoria < 1 || categoria > 10) {
            request.setAttribute("error",
                    "La categoría debe estar entre 1 y 10.");

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
            return;
        }

        if (anyosTrabajados < 0) {
            request.setAttribute("error",
                    "Los años trabajados no pueden ser negativos.");

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
            return;
        }

        Empleado empleado = new Empleado(
                nombre,
                dni,
                sexo,
                categoria,
                anyosTrabajados
        );

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try {
            empleadoDAO.crearEmpleado(empleado);

            // Evita repetir el alta si se recarga la página.
            response.sendRedirect(
                    request.getContextPath()
                            + "/empleados/crear?creado=true"
            );

        } catch (SQLException e) {

            // El DNI puede estar ya registrado.
            if ("23000".equals(e.getSQLState())) {
                request.setAttribute("error",
                        "Ya existe un empleado o una nómina con ese DNI.");
            } else {
                throw new ServletException(
                        "No se pudo registrar el empleado.", e);
            }

            request.getRequestDispatcher("/views/crear-empleado.jsp")
                    .forward(request, response);
        }
    }
}
