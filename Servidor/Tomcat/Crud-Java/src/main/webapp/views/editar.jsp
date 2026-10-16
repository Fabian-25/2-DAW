<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Editar Producto</title>
</head>
<body>
  <h1>Editar Producto</h1>
  <form action="productos" method="post">
    <input type="hidden" name="opcion" value="editar">
    <input type="hidden" name="id" value="${producto.id}">
    <table border="1">
      <tr>
        <td>Nombre:</td>
        <td><input type="text" name="nombre" size="50" value="${producto.nombre}"></td>
      </tr>
      <tr>
        <td>Cantidad:</td>
        <td><input type="text" name="cantidad" size="50" value="${producto.cantidad}"></td>
      </tr>
      <tr>
        <td>Precio:</td>
        <td><input type="text" name="precio" size="50" value="${producto.precio}"></td>
      </tr>
    </table>
    <input type="submit" value="Guardar">
  </form>
</body>
</html>