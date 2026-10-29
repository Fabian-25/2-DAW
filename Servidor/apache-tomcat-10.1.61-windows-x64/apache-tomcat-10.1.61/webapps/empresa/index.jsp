
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de empleados</title>

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

        header h2 {
            margin: 0;
            color: #2563eb;
        }

        main {
            max-width: 850px;
            margin: 65px auto;
            padding: 0 20px;
        }

        .bienvenida {
            text-align: center;
            margin-bottom: 35px;
        }

        .bienvenida h1 {
            font-size: 30px;
            margin-bottom: 12px;
        }

        .bienvenida p {
            color: #64748b;
            line-height: 1.6;
        }

        .menu {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 22px;
        }

        .tarjeta {
            background: white;
            padding: 28px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.06);
            border: 1px solid #e2e8f0;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .tarjeta:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 22px rgba(0, 0, 0, 0.1);
        }

        .tarjeta h3 {
            margin-top: 0;
            margin-bottom: 12px;
            color: #1e293b;
        }

        .tarjeta p {
            color: #64748b;
            line-height: 1.6;
            min-height: 70px;
        }

        .boton {
            display: inline-block;
            margin-top: 12px;
            padding: 11px 17px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 7px;
            font-size: 14px;
            font-weight: bold;
            transition: background 0.2s;
        }

        .boton:hover {
            background: #1d4ed8;
        }

        footer {
            margin-top: 70px;
            padding: 20px;
            text-align: center;
            color: #94a3b8;
            font-size: 13px;
        }

        @media (max-width: 600px) {
            header {
                padding: 20px;
            }

            main {
                margin-top: 40px;
            }

            .menu {
                grid-template-columns: 1fr;
            }

            .bienvenida h1 {
                font-size: 25px;
            }
        }
    </style>
</head>

<body>

<header>
    <h2>Empresa</h2>
</header>

<main>
    <section class="bienvenida">
        <h1>Gestión de empleados</h1>
        <p>
            Bienvenido al sistema de gestión de empleados y nóminas.
            Selecciona una opción para continuar.
        </p>
    </section>

    <section class="menu">
        <div class="tarjeta">
            <h3>Listado de empleados</h3>
            <p>
                Consulta los empleados registrados, sus datos
                personales y la información de sus nóminas.
            </p>
            <a class="boton"
               href="${pageContext.request.contextPath}/empleados">
                Ver empleados
            </a>
        </div>

        <div class="tarjeta">
            <h3>Consultar salario</h3>
            <p>
                Busca a un empleado por su DNI y consulta
                el sueldo que tiene registrado en la base de datos.
            </p>
            <a class="boton"
               href="${pageContext.request.contextPath}/consulta-salario">
                Consultar salario
            </a>
        </div>

        <div class="tarjeta">
            <h3>Buscar empleados</h3>
            <p>
                Encuentra empleados utilizando su nombre,
                DNI, sexo, categoría o años trabajados.
            </p>
            <a class="boton"
               href="${pageContext.request.contextPath}/buscar-empleados">
                Buscar empleados
            </a>
        </div>

        <div class="tarjeta">
            <h3>Crear empleado</h3>
            <p>
                Registra un nuevo empleado y calcula
                automáticamente su sueldo inicial.
            </p>
            <a class="boton"
               href="${pageContext.request.contextPath}/empleados/crear">
                Nuevo empleado
            </a>
        </div>
    </section>
</main>

<footer>
    Empresa · Gestión de empleados y nóminas
</footer>

</body>
</html>
