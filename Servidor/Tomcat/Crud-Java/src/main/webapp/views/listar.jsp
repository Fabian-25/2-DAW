<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>Listar Productos</title>
</head>
<body>
  <h1>Listar Productos</h1>
  <table border="1">
    <tr>
      <td>Id</td>
      <td>Nombre</td>
      <td>Cantidad</td>
      <td>Precio</td>
      <td>Fecha Creacion</td>
      <td>Fecha Actualizacion</td>
      <td>Accion</td>
    </tr>
    <c:forEach var="producto" items="${lista}">
      <tr>
        <td>
          <a href="productos?opcion=meditar&id=<c:out value="${producto.id}"/>">
            <c:out value="${producto.id}"/>
          </a>
        </td>
        <td><c:out value="${producto.nombre}"/></td>
        <td><c:out value="${producto.cantidad}"/></td>
        <td><c:out value="${producto.precio}"/></td>
        <td><c:out value="${producto.fechaCrear}"/></td>
        <td><c:out value="${producto.fechaActualizar}"/></td>
        <td>
          <a href="productos?opcion=eliminar&id=<c:out value="${producto.id}"/>">Eliminar</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>