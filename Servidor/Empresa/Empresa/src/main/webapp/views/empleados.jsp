
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de empleados</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            color: #1e293b;
        }

        header {
            background: #ffffff;
            padding: 20px 40px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.06);
        }

        header a {
            color: #2563eb;
            text-decoration: none;
            font-size: 21px;
            font-weight: bold;
        }

        main {
            max-width: 1150px;
            margin: 45px auto;
            padding: 0 20px;
        }

        .encabezado {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 20px;
            margin-bottom: 25px;
        }

        h1 {
            margin: 0 0 10px;
            font-size: 28px;
        }

        .descripcion {
            margin: 0;
            color: #64748b;
            line-height: 1.5;
        }

        .boton {
            display: inline-block;
            padding: 11px 16px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 7px;
            font-size: 14px;
            font-weight: bold;
            white-space: nowrap;
            transition: background 0.2s;
        }

        .boton:hover {
            background: #1d4ed8;
        }

        .contenedor-tabla {
            overflow-x: auto;
            background: white;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.06);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }

        thead {
            background: #eff6ff;
        }

        th {
            color: #1e40af;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.4px;
        }

        th, td {
            padding: 16px 18px;
            border-bottom: 1px solid #e2e8f0;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        tbody tr:hover {
            background: #f8fafc;
        }

        .sueldo {
            color: #15803d;
            font-weight: bold;
            white-space: nowrap;
        }

        .vacio {
            padding: 35px;
            text-align: center;
            color: #64748b;
            line-height: 1.8;
        }

        .acciones {
            margin-top: 25px;
        }

        footer {
            margin-top: 60px;
            padding: 20px;
            text-align: center;
            color: #94a3b8;
            font-size: 13px;
        }

        @media (max-width: 650px) {
            header {
                padding: 20px;
            }

            .encabezado {
                align-items: flex-start;
                flex-direction: column;
            }

            h1 {
                font-size: 24px;
            }

            th, td {
                padding: 13px;
                font-size: 13px;
            }

        }
        .boton-eliminar {
                        background-color: #dc2626;
                        color: white;
                        border: none;
                        padding: 8px 12px;
                        border-radius: 6px;
                        cursor: pointer;
                        font-size: 14px;
                    }

                    .boton-eliminar:hover {
                        background-color: #b91c1c;
                    }
    </style>
</head>

<body>

<header>
    <a href="${pageContext.request.contextPath}/">Empresa</a>
</header>

<main>
    <section class="encabezado">
        <div>
            <h1>Listado de empleados</h1>
            <p class="descripcion">
                Consulta los datos de los trabajadores y sus nóminas.
            </p>
        </div>

        <a class="boton"
           href="${pageContext.request.contextPath}/empleados/crear">
            + Nuevo empleado
        </a>
    </section>

    <div class="contenedor-tabla">
        <c:choose>
            <c:when test="${empty empleados}">
                <div class="vacio">
                    <h3>No hay empleados registrados</h3>
                    <p>Registra un empleado para que aparezca en este listado.</p>
                    <a class="boton"
                       href="${pageContext.request.contextPath}/empleados/crear">
                        Crear empleado
                    </a>
                </div>
            </c:when>

            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>DNI</th>
                        <th>Sexo</th>
                        <th>Categoría</th>
                        <th>Antigüedad</th>
                        <th>Sueldo</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="empleado" items="${empleados}">
                        <tr>
                            <td><c:out value="${empleado.nombre}"/></td>
                            <td><c:out value="${empleado.dni}"/></td>
                            <td><c:out value="${empleado.sexo}"/></td>
                            <td><c:out value="${empleado.categoria}"/></td>
                            <td>
                                <c:out value="${empleado.anyosTrabajados}"/>
                                años
                            </td>
                            <td class="sueldo">
                                <c:out value="${empleado.sueldo / 100.0}"/> €
                            </td>

<td>
    <a class="boton"
       href="${pageContext.request.contextPath}/empleados/editar?dni=${empleado.dni}">
        Editar
    </a>

    <form action="${pageContext.request.contextPath}/empleados/eliminar"
          method="post"
          style="display: inline-block;"
          onsubmit="return confirm('¿Seguro que quieres eliminar este empleado? Esta acción no se puede deshacer.');">

        <input type="hidden" name="dni" value="${empleado.dni}">

        <button type="submit" class="boton-eliminar">
            Eliminar
        </button>
    </form>
</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="acciones">
        <a class="boton"
           href="${pageContext.request.contextPath}/">
            ← Volver al menú
        </a>
    </div>
</main>

<footer>
    Empresa · Gestión de empleados y nóminas
</footer>

</body>
</html>
