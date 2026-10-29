<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Consultar salario - Empresa</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 30px 20px;
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            color: #263238;
        }

        .contenedor {
            max-width: 700px;
            margin: 0 auto;
        }

        .cabecera {
            margin-bottom: 25px;
        }

        .cabecera h1 {
            margin-bottom: 10px;
            color: #1e3a5f;
        }

        .cabecera p {
            color: #64748b;
        }

        .tarjeta {
            padding: 25px;
            margin-bottom: 20px;
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.06);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 18px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            font-size: 16px;
        }

        input:focus {
            outline: 2px solid #93c5fd;
            border-color: #2563eb;
        }

        .boton {
            display: inline-block;
            padding: 11px 18px;
            border: none;
            border-radius: 6px;
            background-color: #2563eb;
            color: white;
            font-size: 15px;
            text-decoration: none;
            cursor: pointer;
        }

        .boton:hover {
            background-color: #1d4ed8;
        }

        .boton-secundario {
            background-color: #64748b;
        }

        .boton-secundario:hover {
            background-color: #475569;
        }

        .error {
            margin-top: 18px;
            padding: 12px;
            border-radius: 6px;
            background-color: #fee2e2;
            color: #991b1b;
        }

        .resultado h2 {
            margin-top: 0;
            color: #1e3a5f;
        }

        .datos {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }

        .datos th,
        .datos td {
            padding: 12px;
            border-bottom: 1px solid #e2e8f0;
            text-align: left;
        }

        .datos th {
            width: 40%;
            background-color: #f8fafc;
        }

        .salario {
            color: #1e3a5f;
            font-size: 24px;
            font-weight: bold;
        }

        .acciones {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-top: 20px;
        }

        @media (max-width: 500px) {
            body {
                padding: 20px 12px;
            }

            .tarjeta {
                padding: 18px;
            }

            .datos th,
            .datos td {
                padding: 9px;
            }
        }
    </style>
</head>

<body>

<div class="contenedor">

    <header class="cabecera">
        <h1>Consultar salario</h1>
        <p>Introduce el DNI para consultar los datos y el sueldo del empleado.</p>
    </header>

    <main>

        <section class="tarjeta">

            <form action="${pageContext.request.contextPath}/consulta-salario"
                  method="post">

                <label for="dni">DNI del empleado</label>

                <input type="text"
                       id="dni"
                       name="dni"
                       placeholder="Ejemplo: 12345678A"
                       maxlength="9"
                       pattern="[0-9]{8}[A-Za-z]"
                       title="Introduce un DNI válido con 8 números y una letra."
                       required>

                <button type="submit" class="boton">
                    Buscar salario
                </button>

            </form>

            <c:if test="${not empty mensajeError}">
                <div class="error" role="alert">
                    <c:out value="${mensajeError}"/>
                </div>
            </c:if>

        </section>

        <c:if test="${not empty empleado}">

            <section class="tarjeta resultado">

                <h2>Datos del empleado</h2>

                <table class="datos">
                    <tr>
                        <th>Nombre</th>
                        <td><c:out value="${empleado.nombre}"/></td>
                    </tr>

                    <tr>
                        <th>DNI</th>
                        <td><c:out value="${empleado.dni}"/></td>
                    </tr>

                    <tr>
                        <th>Sexo</th>
                        <td>
                            <span id="sexoEmpleado"></span>
                        </td>
                    </tr>

                    <tr>
                        <th>Categoría</th>
                        <td><c:out value="${empleado.categoria}"/></td>
                    </tr>

                    <tr>
                        <th>Años trabajados</th>
                        <td><c:out value="${empleado.anyosTrabajados}"/></td>
                    </tr>
                </table>

                <p>Salario registrado en la base de datos:</p>

                <p class="salario">
                    <c:choose>
                        <c:when test="${empleado.sueldo != null}">
                            <fmt:formatNumber
                                    value="${empleado.sueldo / 100.0}"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/> €
                        </c:when>
                        <c:otherwise>
                            No hay un salario registrado.
                        </c:otherwise>
                    </c:choose>
                </p>

            </section>

        </c:if>

        <div class="acciones">
            <a href="${pageContext.request.contextPath}/"
               class="boton boton-secundario">
                Volver al menú
            </a>

            <a href="${pageContext.request.contextPath}/empleados"
               class="boton boton-secundario">
                Ver empleados
            </a>
        </div>

    </main>

</div>
<script>
    const sexoEmpleado = '${empleado.sexo}';
    const elementoSexo = document.getElementById('sexoEmpleado');

    if (elementoSexo) {
        if (sexoEmpleado === 'M') {
            elementoSexo.textContent = 'Masculino';
        } else if (sexoEmpleado === 'F') {
            elementoSexo.textContent = 'Femenino';
        } else {
            elementoSexo.textContent = sexoEmpleado;
        }
    }
</script>

</body>
</html>