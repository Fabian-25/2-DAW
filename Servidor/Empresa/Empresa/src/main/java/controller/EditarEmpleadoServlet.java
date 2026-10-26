
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

@WebServlet("/empleados/editar")
public class EditarEmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        if (dni == null || dni.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Debes indicar el DNI del empleado.");
            return;
        }

        try {
            Empleado empleado =
                    new EmpleadoDAO().obtenerEmpleadoPorDni(dni.trim().toUpperCase());

            if (empleado == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No se ha encontrado el empleado.");
                return;
            }

            request.setAttribute("empleado", empleado);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/views/editar-empleado.jsp");

            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "No se pudo consultar el empleado.", e);
        }
    }

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

        if (dni == null || dni.isBlank()
                || nombre == null || nombre.isBlank()
                || sexoTexto == null || sexoTexto.isBlank()
                || categoriaTexto == null || anyosTexto == null
                || categoriaTexto.isBlank() || anyosTexto.isBlank()) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Debes rellenar todos los campos.");
            return;
        }

        dni = dni.trim().toUpperCase();
        nombre = nombre.trim();
        sexoTexto = sexoTexto.trim().toUpperCase();

        if (!dni.matches("\\d{8}[A-Z]")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El DNI no tiene un formato válido.");
            return;
        }

        if (nombre.length() > 100) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El nombre no puede superar los 100 caracteres.");
            return;
        }

        if (!sexoTexto.equals("M") && !sexoTexto.equals("F")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El sexo debe ser M o F.");
            return;
        }

        int categoria;
        int anyosTrabajados;

        try {
            categoria = Integer.parseInt(categoriaTexto);
            anyosTrabajados = Integer.parseInt(anyosTexto);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "La categoría y los años deben ser números enteros.");
            return;
        }

        if (categoria < 1 || categoria > 10 || anyosTrabajados < 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "La categoría debe estar entre 1 y 10 y los años no pueden ser negativos.");
            return;
        }

        Empleado empleado = new Empleado(
                nombre,
                dni,
                sexoTexto.charAt(0),
                categoria,
                anyosTrabajados
        );

        try {
            EmpleadoDAO dao = new EmpleadoDAO();

            if (dao.obtenerEmpleadoPorDni(dni) == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "El empleado ya no existe.");
                return;
            }

            dao.actualizarEmpleado(empleado);

            response.sendRedirect(
                    request.getContextPath() + "/empleados?actualizado=true");

        } catch (SQLException e) {
            throw new ServletException(
                    "No se pudo actualizar el empleado y su nómina.", e);
        }
    }
}
