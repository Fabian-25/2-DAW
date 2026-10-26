
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear empleado</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f4f8;
            margin: 0;
            padding: 30px 15px;
        }

        .contenedor {
            max-width: 520px;
            margin: auto;
            padding: 25px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 3px 12px #00000015;
        }

        h1 {
            text-align: center;
            color: #243b55;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input, select {
            box-sizing: border-box;
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #bbb;
            border-radius: 5px;
        }

        button, .volver {
            display: inline-block;
            margin-top: 20px;
            padding: 11px 16px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            font-size: 15px;
        }

        .crear {
            background: #2457c5;
            color: white;
        }

        .volver {
            background: #e5e7eb;
            color: #222;
        }

        .mensaje {
            padding: 12px;
            margin-top: 15px;
            border-radius: 5px;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
        }

        .exito {
            background: #dcfce7;
            color: #166534;
        }

        .nota {
            color: #555;
            font-size: 13px;
            margin-top: 15px;
        }
    </style>
</head>

<body>
<div class="contenedor">

    <h1>Crear empleado</h1>

    <c:if test="${not empty error}">
        <div class="mensaje error">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <c:if test="${param.creado == 'true'}">
        <div class="mensaje exito">
            El empleado se ha registrado correctamente.
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/empleados/crear"
          method="post">

        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni"
               maxlength="9" pattern="[0-9]{8}[A-Za-z]"
               placeholder="12345678A" required>

        <label for="nombre">Nombre completo:</label>
        <input type="text" id="nombre" name="nombre"
               maxlength="100" required>

        <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo" required>
            <option value="">Selecciona una opción</option>
            <option value="M">Masculino (M)</option>
            <option value="F">Femenino (F)</option>
        </select>

        <label for="categoria">Categoría:</label>
        <select id="categoria" name="categoria" required>
            <option value="">Selecciona una categoría</option>
            <c:forEach begin="1" end="10" var="i">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>

        <label for="anyosTrabajados">Años trabajados:</label>
        <input type="number" id="anyosTrabajados"
               name="anyosTrabajados"
               min="0" step="1" value="0" required>

        <p class="nota">
            El sueldo se calculará automáticamente según la categoría
            y los años trabajados.
        </p>

        <button type="submit" class="crear">
            Registrar empleado
        </button>

        <a class="volver"
           href="${pageContext.request.contextPath}/empleados">
            Volver
        </a>
    </form>

</div>
</body>
</html>
