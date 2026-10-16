
package controller;

import dao.ProductoDAO;
import model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private final ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("nuevo".equals(accion)) {
            request.getRequestDispatcher("/views/crear.jsp")
                    .forward(request, response);

        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Producto producto = dao.buscarPorId(id);

            if (producto == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Producto no encontrado");
                return;
            }

            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/views/editar.jsp")
                    .forward(request, response);

        } else {
            request.setAttribute("productos", dao.listar());
            request.getRequestDispatcher("/views/listar.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            if ("insertar".equals(accion)) {
                Producto producto = new Producto(
                        request.getParameter("nombre"),
                        request.getParameter("descripcion"),
                        Double.parseDouble(request.getParameter("precio")),
                        Integer.parseInt(request.getParameter("stock"))
                );

                dao.insertar(producto);

            } else if ("actualizar".equals(accion)) {
                Producto producto = new Producto(
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("nombre"),
                        request.getParameter("descripcion"),
                        Double.parseDouble(request.getParameter("precio")),
                        Integer.parseInt(request.getParameter("stock"))
                );

                dao.actualizar(producto);

            } else if ("eliminar".equals(accion)) {
                dao.eliminar(Integer.parseInt(request.getParameter("id")));
            }

            response.sendRedirect(request.getContextPath() + "/productos");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El precio, el stock o el ID no tienen un formato válido");
        }
    }
}
