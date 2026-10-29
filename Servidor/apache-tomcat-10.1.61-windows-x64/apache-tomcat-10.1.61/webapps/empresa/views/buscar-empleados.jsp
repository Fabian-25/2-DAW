<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Buscar empleados - Empresa</title>

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
            padding: 20px 40px;
            background: white;
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
            margin: 40px auto;
            padding: 0 20px;
        }

        h1 {
            color: #1e3a5f;
            margin-bottom: 10px;
        }

        .descripcion {
            color: #64748b;
            line-height: 1.6;
            margin-bottom: 25px;
        }

        .tarjeta {
            padding: 25px;
            margin-bottom: 25px;
            background: white;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
        }

        .formulario {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 20px;
        }

        .campo label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        .campo input,
        .campo select {
            width: 100%;
            min-height: 43px;
            padding: 10px 12px;
            border: 1px solid #cbd5e1;
            border-radius: 7px;
            background: white;
            font-size: 15px;
        }

        .campo input:focus,
        .campo select:focus {
            outline: 2px solid #93c5fd;
            border-color: #2563eb;
        }

        .acciones {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            grid-column: 1 / -1;
            margin-top: 5px;
        }

        .boton {
            display: inline-block;
            padding: 12px 17px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 7px;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
        }

        .boton:hover {
            background: #1d4ed8;
        }

        .boton-secundario {
            background: #64748b;
        }

        .boton-secundario:hover {
            background: #475569;
        }

        .error {
            padding: 14px;
            margin-bottom: 20px;
            background: #fee2e2;
            color: #991b1b;
            border-radius: 7px;
        }

        .vacio {
            padding: 25px;
            text-align: center;
            color: #64748b;
        }

        .contenedor-tabla {
            overflow-x: auto;
            border: 1px solid #e2e8f0;
            border-radius: 8px;
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
        }

        th,
        td {
            padding: 14px;
            border-bottom: 1px solid #e2e8f0;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        tbody tr:hover {
            background: #f8fafc;
        }

        .sueldo {
            font-weight: bold;
            white-space: nowrap;
        }

        footer {
            padding: 20px;
            margin-top: 50px;
            color: #94a3b8;
            text-align: center;
            font-size: 13px;
        }

        @media (max-width: 650px) {
            header {
                padding: 20px;
            }

            main {
                margin-top: 25px;
                padding: 0 12px;
            }

            .tarjeta {
                padding: 18px;
            }

            .formulario {
                grid-template-columns: 1fr;
            }

            th,
            td {
                padding: 10px;
                font-size: 13px;
            }
        }
    </style>
</head>

<body>

<header>
    <a href="${pageContext.request.contextPath}/">Empresa</a>
</header>

<main>

    <h1>Buscar empleados</h1>

    <p class="descripcion">
        Puedes utilizar un solo criterio o combinar varios.
        Los campos vacíos no se tendrán en cuenta.
    </p>

    <section class="tarjeta">

        <form action="${pageContext.request.contextPath}/buscar-empleados"
              method="post"
              class="formulario">

            <div class="campo">
                <label for="nombre">Nombre</label>

                <input type="text"
                       id="nombre"
                       name="nombre"
                       maxlength="100"
                       value="${nombre}">
            </div>

            <div class="campo">
                <label for="dni">DNI</label>

                <input type="text"
                       id="dni"
                       name="dni"
                       maxlength="9"
                       value="${dni}">
            </div>

            <div class="campo">
                <label for="sexo">Sexo</label>

                <select id="sexo" name="sexo">
                    <option value="">Todos</option>
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                </select>
            </div>

            <div class="campo">
                <label for="categoria">Categoría</label>

                <select id="categoria" name="categoria">
                    <option value="">Todas</option>

                    <c:forEach var="numero" begin="1" end="10">
                        <option value="${numero}">
                            ${numero}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="campo">
                <label for="anyosTrabajados">Años trabajados</label>

                <input type="number"
                       id="anyosTrabajados"
                       name="anyosTrabajados"
                       min="0"
                       step="1"
                       value="${anyosTrabajados}">
            </div>

            <div class="acciones">
                <button type="submit" class="boton">
                    Buscar empleados
                </button>

                <a class="boton boton-secundario"
                   href="${pageContext.request.contextPath}/buscar-empleados">
                    Limpiar búsqueda
                </a>
            </div>

        </form>

    </section>

    <c:if test="${not empty mensajeError}">
        <div class="error" role="alert">
            <c:out value="${mensajeError}"/>
        </div>
    </c:if>

    <c:if test="${busquedaRealizada}">

        <section class="tarjeta">

            <h2>Resultados de la búsqueda</h2>

            <c:choose>

                <c:when test="${empty empleados}">
                    <div class="vacio">
                        No se han encontrado empleados con esos criterios.
                    </div>
                </c:when>

                <c:otherwise>

                    <p>
                        Empleados encontrados:
                        <c:out value="${empleados.size()}"/>
                    </p>

                    <div class="contenedor-tabla">

                        <table>
                            <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>DNI</th>
                                <th>Sexo</th>
                                <th>Categoría</th>
                                <th>Antigüedad</th>
                                <th>Sueldo</th>
                            </tr>
                            </thead>

                            <tbody>

                            <c:forEach var="empleado" items="${empleados}">

                                <tr>
                                    <td>
                                        <c:out value="${empleado.nombre}"/>
                                    </td>

                                    <td>
                                        <c:out value="${empleado.dni}"/>
                                    </td>

                                    <td>
                                        <span class="sexo-valor">
                                            <c:out value="${empleado.sexo}"/>
                                        </span>
                                    </td>

                                    <td>
                                        <c:out value="${empleado.categoria}"/>
                                    </td>

                                    <td>
                                        <c:out value="${empleado.anyosTrabajados}"/>
                                        años
                                    </td>

                                    <td class="sueldo">

                                        <c:choose>
                                            <c:when test="${not empty empleado.sueldo}">
                                                <fmt:formatNumber
                                                        value="${empleado.sueldo / 100.0}"
                                                        minFractionDigits="2"
                                                        maxFractionDigits="2"/> €
                                            </c:when>

                                            <c:otherwise>
                                                Sin nómina
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                    </div>

                </c:otherwise>

            </c:choose>

        </section>

    </c:if>

    <a class="boton boton-secundario"
       href="${pageContext.request.contextPath}/">
        Volver al menú
    </a>

</main>

<script>
    // Recupera los valores seleccionados sin comparar caracteres en JSTL.
    const sexoGuardado = '${sexo}';
    const categoriaGuardada = '${categoria}';

    document.getElementById('sexo').value = sexoGuardado;
    document.getElementById('categoria').value = categoriaGuardada;

    // Traduce los valores del sexo en la tabla de resultados.
    document.querySelectorAll('.sexo-valor').forEach(function (elemento) {
        if (elemento.textContent.trim() === 'M') {
            elemento.textContent = 'Masculino';
        } else if (elemento.textContent.trim() === 'F') {
            elemento.textContent = 'Femenino';
        }
    });
</script>

<footer>
    Empresa · Gestión de empleados y nóminas
</footer>

</body>
</html>