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
 * Servlet encargado de mostrar y actualizar los datos de un empleado.
 *
 * Permite consultar los datos de un empleado para mostrarlos en el
 * formulario de edición y guardar los cambios realizados.
 *
 * @version 1.0
 */
@WebServlet("/empleados/editar")
public class EditarEmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Muestra el formulario de edición con los datos del empleado seleccionado.
     *
     * Obtiene el DNI desde la petición, consulta el empleado en la base de datos
     * y envía sus datos a la página JSP correspondiente.
     *
     * @param request petición HTTP que contiene el DNI del empleado.
     * @param response respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error al procesar la petición.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el DNI del empleado que se quiere editar.
        String dni = request.getParameter("dni");

        // Comprueba que se haya indicado un DNI.
        if (dni == null || dni.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Debes indicar el DNI del empleado.");
            return;
        }

        try {
            // Busca al empleado en la base de datos.
            Empleado empleado =
                    new EmpleadoDAO().obtenerEmpleadoPorDni(dni.trim().toUpperCase());

            // Si no existe, devuelve un error 404.
            if (empleado == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No se ha encontrado el empleado.");
                return;
            }

            // Pasa los datos del empleado a la vista JSP.
            request.setAttribute("empleado", empleado);

            // Selecciona la página que contiene el formulario de edición.
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/views/editar-empleado.jsp");

            // Envía la petición a la página JSP.
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            // Convierte el error de base de datos en una excepción del servlet.
            throw new ServletException(
                    "No se pudo consultar el empleado.", e);
        }
    }

    /**
     * Procesa el formulario de edición y actualiza los datos del empleado.
     *
     * Comprueba los campos recibidos, valida sus valores y solicita al DAO
     * que actualice el empleado y su nómina.
     *
     * @param request petición HTTP con los nuevos datos del empleado.
     * @param response respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error al actualizar los datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Permite recibir correctamente caracteres especiales y tildes.
        request.setCharacterEncoding("UTF-8");

        // Recupera los valores enviados desde el formulario.
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexoTexto = request.getParameter("sexo");
        String categoriaTexto = request.getParameter("categoria");
        String anyosTexto = request.getParameter("anyosTrabajados");

        // Comprueba que todos los campos obligatorios estén completos.
        if (dni == null || dni.isBlank()
                || nombre == null || nombre.isBlank()
                || sexoTexto == null || sexoTexto.isBlank()
                || categoriaTexto == null || anyosTexto == null
                || categoriaTexto.isBlank() || anyosTexto.isBlank()) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Debes rellenar todos los campos.");
            return;
        }

        // Elimina espacios innecesarios y normaliza los valores de texto.
        dni = dni.trim().toUpperCase();
        nombre = nombre.trim();
        sexoTexto = sexoTexto.trim().toUpperCase();

        // Comprueba que el DNI tenga ocho números y una letra.
        if (!dni.matches("\\d{8}[A-Z]")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El DNI no tiene un formato válido.");
            return;
        }

        // Limita la longitud máxima permitida para el nombre.
        if (nombre.length() > 100) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El nombre no puede superar los 100 caracteres.");
            return;
        }

        // Comprueba que el sexo sea uno de los valores admitidos.
        if (!sexoTexto.equals("M") && !sexoTexto.equals("F")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El sexo debe ser M o F.");
            return;
        }

        int categoria;
        int anyosTrabajados;

        try {
            // Convierte la categoría y los años de trabajo a números enteros.
            categoria = Integer.parseInt(categoriaTexto);
            anyosTrabajados = Integer.parseInt(anyosTexto);
        } catch (NumberFormatException e) {
            // Informa al usuario si alguno de los valores no es numérico.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "La categoría y los años deben ser números enteros.");
            return;
        }

        // Comprueba que la categoría esté entre 1 y 10 y que los años no sean negativos.
        if (categoria < 1 || categoria > 10 || anyosTrabajados < 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "La categoría debe estar entre 1 y 10 y los años no pueden ser negativos.");
            return;
        }

        // Crea un objeto Empleado con los datos actualizados del formulario.
        Empleado empleado = new Empleado(
                nombre,
                dni,
                sexoTexto.charAt(0),
                categoria,
                anyosTrabajados
        );

        try {
            EmpleadoDAO dao = new EmpleadoDAO();

            // Comprueba que el empleado siga existiendo antes de actualizarlo.
            if (dao.obtenerEmpleadoPorDni(dni) == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "El empleado ya no existe.");
                return;
            }

            // Actualiza los datos del empleado y su nómina.
            dao.actualizarEmpleado(empleado);

            // Regresa al listado mostrando que la actualización se ha realizado.
            response.sendRedirect(
                    request.getContextPath() + "/empleados?actualizado=true");

        } catch (SQLException e) {
            // Informa al contenedor del servlet de que se ha producido un error SQL.
            throw new ServletException(
                    "No se pudo actualizar el empleado y su nómina.", e);
        }
    }
}