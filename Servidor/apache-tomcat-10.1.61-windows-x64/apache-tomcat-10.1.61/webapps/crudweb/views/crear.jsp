<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Crear Producto</title>
</head>
<body>
  <h1>Crear Producto</h1>
  <form action="productos" method="post">
    <input type="hidden" name="opcion" value="guardar">
    <table border="1">
      <tr>
        <td>Nombre:</td>
        <td><input type="text" name="nombre" size="50"></td>
      </tr>
      <tr>
        <td>Cantidad:</td>
        <td><input type="text" name="cantidad" size="50"></td>
      </tr>
      <tr>
        <td>Precio:</td>
        <td><input type="text" name="precio" size="50"></td>
      </tr>
    </table>
    <input type="submit" value="Guardar">
  </form>
</body>
</html>