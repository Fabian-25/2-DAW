package dao;

import conexion.ConexionDB;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // guardar producto
    public boolean guardar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, cantidad, precio, fecha_crear, fecha_actualizar) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            ps.setDate(4, producto.getFechaCrear());
            ps.setDate(5, producto.getFechaActualizar());

            return ps.executeUpdate() > 0;
        }
    }

    // editar producto
    public boolean editar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            ps.setDate(4, producto.getFechaActualizar());
            ps.setInt(5, producto.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // eliminar producto
    public boolean eliminar(int idProducto) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            return ps.executeUpdate() > 0;
        }
    }

    // obtener lista de productos
    public List<Producto> obtenerProductos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY id";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // obtener un producto
    public Producto obtenerProducto(int idProducto) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return new Producto();
    }

    private Producto mapear(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCantidad(rs.getDouble("cantidad"));
        p.setPrecio(rs.getDouble("precio"));
        p.setFechaCrear(rs.getDate("fecha_crear"));
        p.setFechaActualizar(rs.getDate("fecha_actualizar"));
        return p;
    }
}