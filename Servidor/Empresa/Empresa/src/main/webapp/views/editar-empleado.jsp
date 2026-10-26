
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar empleado</title>

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
            background: white;
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
            max-width: 650px;
            margin: 45px auto;
            padding: 0 20px;
        }

        .tarjeta {
            background: white;
            padding: 32px;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.06);
        }

        h1 {
            margin-top: 0;
            margin-bottom: 10px;
        }

        .descripcion {
            color: #64748b;
            line-height: 1.6;
            margin-bottom: 28px;
        }

        .campo {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            font-size: 14px;
        }

        input, select {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 7px;
            background: white;
            color: #1e293b;
            font-size: 15px;
        }

        input:focus, select:focus {
            outline: 2px solid #bfdbfe;
            border-color: #2563eb;
        }

        input[readonly] {
            background: #f1f5f9;
            color: #64748b;
        }

        .botones {
            display: flex;
            gap: 12px;
            margin-top: 28px;
            flex-wrap: wrap;
        }

        .boton {
            display: inline-block;
            padding: 12px 18px;
            border: none;
            border-radius: 7px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            font-weight: bold;
            font-size: 14px;
            cursor: pointer;
        }

        .boton:hover {
            background: #1d4ed8;
        }

        .secundario {
            background: #e2e8f0;
            color: #334155;
        }

        .secundario:hover {
            background: #cbd5e1;
        }

        .aviso {
            padding: 13px;
            border-radius: 7px;
            background: #eff6ff;
            color: #1d4ed8;
            font-size: 14px;
            line-height: 1.5;
            margin-top: 22px;
        }

        footer {
            text-align: center;
            color: #94a3b8;
            font-size: 13px;
            padding: 25px;
        }

        @media (max-width: 600px) {
            header {
                padding: 20px;
            }

            .tarjeta {
                padding: 23px;
            }
        }
    </style>
</head>

<body>

<header>
    <a href="${pageContext.request.contextPath}/">Empresa</a>
</header>

<main>
    <div class="tarjeta">
        <h1>Editar empleado</h1>

        <p class="descripcion">
            Modifica los datos del trabajador. Su nómina se actualizará
            automáticamente al guardar los cambios.
        </p>

        <form method="post"
              action="${pageContext.request.contextPath}/empleados/editar">

            <div class="campo">
                <label for="dni">DNI</label>
                <input type="text" id="dni" name="dni"
                       value="<c:out value='${empleado.dni}'/>"
                       readonly>
            </div>

            <div class="campo">
                <label for="nombre">Nombre completo</label>
                <input type="text" id="nombre" name="nombre"
                       maxlength="100" required
                       value="<c:out value='${empleado.nombre}'/>">
            </div>

            <div class="campo">
                <label for="sexo">Sexo</label>
                <select id="sexo" name="sexo" required>
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                </select>
            </div>

            <div class="campo">
                <label for="categoria">Categoría profesional</label>
                <select id="categoria" name="categoria" required>
                    <c:forEach begin="1" end="10" var="i">
                        <option value="${i}"
                            ${empleado.categoria == i ? 'selected' : ''}>
                            Categoría ${i}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="campo">
                <label for="anyosTrabajados">Años trabajados</label>
                <input type="number" id="anyosTrabajados"
                       name="anyosTrabajados"
                       min="0" max="100" required
                       value="${empleado.anyosTrabajados}">
            </div>

            <div class="botones">
                <button class="boton" type="submit">
                    Guardar cambios
                </button>

                <a class="boton secundario"
                   href="${pageContext.request.contextPath}/empleados">
                    Cancelar
                </a>
            </div>

            <div class="aviso">
                El sueldo se recalculará según la categoría y los años trabajados.
            </div>
        </form>
    </div>
</main>

<footer>
    Empresa · Gestión de empleados y nóminas
</footer>
<script>
    const sexoGuardado = '${empleado.sexo}';
    const selectorSexo = document.getElementById('sexo');

    selectorSexo.value = sexoGuardado;
</script>

</body>
</html>
