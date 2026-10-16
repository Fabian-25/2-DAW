package controller;

import dao.ProductoDAO;
import model.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");
        if (opcion == null) {
            opcion = "listar";
        }

        ProductoDAO productoDAO = new ProductoDAO();

        try {
            if (opcion.equals("crear")) {
                System.out.println("Usted a presionado la opcion crear");
                RequestDispatcher rd = request.getRequestDispatcher("/views/crear.jsp");
                rd.forward(request, response);

            } else if (opcion.equals("listar")) {
                List<Producto> lista = productoDAO.obtenerProductos();
                for (Producto producto : lista) {
                    System.out.println(producto);
                }
                request.setAttribute("lista", lista);
                RequestDispatcher rd = request.getRequestDispatcher("/views/listar.jsp");
                rd.forward(request, response);
                System.out.println("Usted a presionado la opcion listar");

            } else if (opcion.equals("meditar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println("Editar id: " + id);
                Producto p = productoDAO.obtenerProducto(id);
                System.out.println(p);
                request.setAttribute("producto", p);
                RequestDispatcher rd = request.getRequestDispatcher("/views/editar.jsp");
                rd.forward(request, response);

            } else if (opcion.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                productoDAO.eliminar(id);
                System.out.println("Registro eliminado satisfactoriamente...");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no válido");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String opcion = request.getParameter("opcion");
        Date fechaActual = new Date();
        ProductoDAO productoDAO = new ProductoDAO();

        try {
            if ("guardar".equals(opcion)) {
                Producto producto = new Producto();
                producto.setNombre(request.getParameter("nombre"));
                producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));

                productoDAO.guardar(producto);
                System.out.println("Registro guardado satisfactoriamente...");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);

            } else if ("editar".equals(opcion)) {
                Producto producto = new Producto();
                producto.setId(Integer.parseInt(request.getParameter("id")));
                producto.setNombre(request.getParameter("nombre"));
                producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));

                productoDAO.editar(producto);
                System.out.println("Registro editado satisfactoriamente...");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "La cantidad, el precio o el ID no tienen un formato válido");
        }
    }
}