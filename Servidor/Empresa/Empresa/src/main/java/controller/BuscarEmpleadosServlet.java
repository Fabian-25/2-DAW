package controller;

import dao.EmpleadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empleado;

import java.io.IOException;
import java.util.List;

/**
 * Permite buscar empleados por nombre, DNI, sexo,
 * categoría y años trabajados.
 */
@WebServlet("/buscar-empleados")
public class BuscarEmpleadosServlet extends HttpServlet {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Muestra el formulario de búsqueda.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/buscar-empleados.jsp")
                .forward(request, response);
    }

    /**
     * Procesa los criterios y muestra los resultados.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = limpiar(request.getParameter("nombre"));
        String dni = limpiar(request.getParameter("dni"));
        String sexo = limpiar(request.getParameter("sexo"));
        String categoriaTexto = limpiar(request.getParameter("categoria"));
        String anyosTexto = limpiar(request.getParameter("anyosTrabajados"));

        // Conservamos los valores introducidos en el formulario.
        request.setAttribute("nombre", nombre);
        request.setAttribute("dni", dni);
        request.setAttribute("sexo", sexo);
        request.setAttribute("categoria", categoriaTexto);
        request.setAttribute("anyosTrabajados", anyosTexto);

        try {
            Integer categoria = null;
            Integer anyosTrabajados = null;

            if (categoriaTexto != null) {
                categoria = Integer.parseInt(categoriaTexto);

                if (categoria < 1 || categoria > 10) {
                    throw new IllegalArgumentException(
                            "La categoría debe estar entre 1 y 10."
                    );
                }
            }

            if (anyosTexto != null) {
                anyosTrabajados = Integer.parseInt(anyosTexto);

                if (anyosTrabajados < 0) {
                    throw new IllegalArgumentException(
                            "Los años trabajados no pueden ser negativos."
                    );
                }
            }

            if (sexo != null
                    && !sexo.equals("M")
                    && !sexo.equals("F")) {

                throw new IllegalArgumentException(
                        "El sexo seleccionado no es válido."
                );
            }

            // No permitimos buscar sin introducir ningún criterio.
            if (nombre == null
                    && dni == null
                    && sexo == null
                    && categoria == null
                    && anyosTrabajados == null) {

                request.setAttribute(
                        "mensajeError",
                        "Introduce al menos un criterio de búsqueda."
                );

            } else {
                List<Empleado> empleados =
                        empleadoDAO.buscarEmpleados(
                                nombre,
                                dni,
                                sexo,
                                categoria,
                                anyosTrabajados
                        );

                request.setAttribute("empleados", empleados);
                request.setAttribute("busquedaRealizada", true);
            }

        } catch (NumberFormatException e) {
            request.setAttribute(
                    "mensajeError",
                    "La categoría y los años deben ser números enteros."
            );

        } catch (IllegalArgumentException e) {
            request.setAttribute("mensajeError", e.getMessage());

        } catch (Exception e) {
            getServletContext().log(
                    "Error al buscar empleados",
                    e
            );

            request.setAttribute(
                    "mensajeError",
                    "No se ha podido realizar la búsqueda. " +
                            "Comprueba la conexión con la base de datos."
            );
        }

        request.getRequestDispatcher("/views/buscar-empleados.jsp")
                .forward(request, response);
    }

    /**
     * Convierte las cadenas vacías en null y elimina espacios.
     */
    private String limpiar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }

        return valor.trim();
    }
}