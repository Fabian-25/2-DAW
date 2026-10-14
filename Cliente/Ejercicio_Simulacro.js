// ==========================================
// FUNCIÓN AUXILIAR PARA ROBUSTEZ
// Como el examen pide que no falle con datos incorrectos, 
// comprobamos si el dato que nos pasan es un Array de verdad[cite: 5].
// ==========================================
const esArray = (dato) => Array.isArray(dato);


// ==========================================
// EJERCICIO 1: Código defectuoso[cite: 1]
// ==========================================
const calcularPrecioFinal = (precio, descuento) => {
    // 1. Convertimos a número por si nos pasan un texto como "120"[cite: 1]
    const precioNum = Number(precio);
    const descNum = Number(descuento);

    // 2. Robustez: Si al convertir a número da NaN (Not a Number), devolvemos null[cite: 5]
    if (isNaN(precioNum) || isNaN(descNum)) return null; 

    // 3. Tu misma lógica, pero retornando el valor directamente
    if (precioNum >= 100) {
        const precioFinal = precioNum - (precioNum * descNum / 100);
        return precioFinal;
    }
    
    return precioNum;
};


// ==========================================
// EJERCICIO 2: Resultados académicos[cite: 2]
// ==========================================
const obtenerEstadisticas = (alumnos) => {
    // Robustez: si no es un array, devolvemos null[cite: 5]
    if (!esArray(alumnos)) return null;

    // Usamos variables let como hiciste tú porque van a cambiar de valor
    let aprobados = 0;
    let suspensos = 0;
    let totalnota = 0;

    // Tu bucle for...of es perfectamente válido en JS Moderno
    for (const a of alumnos) {
        if (a.nota >= 5) {
            aprobados++;
        } else {
            suspensos++;
        }
        totalnota += a.nota;
    }

    // Declaramos la media con const
    const notamedia = totalnota / alumnos.length;

    // El examen pide devolver un objeto, no imprimirlo[cite: 2]
    return {
        aprobados: aprobados,
        suspensos: suspensos,
        notaMedia: notamedia
    };
};


// ==========================================
// EJERCICIO 3: Catálogo de productos[cite: 3]
// ==========================================

// a)
const obtenerNombresDisponibles = (productos, precioMaximo) => {
    if (!esArray(productos)) return null;

    return productos
        // filter: se queda solo con los productos que cumplen TU condición[cite: 3]
        .filter(p => p.stock > 0 && p.precio <= precioMaximo)
        // map: coge esos productos filtrados y extrae solo su nombre[cite: 3]
        .map(p => p.nombre); 
};

// b)
const buscarProductoDisponible = (productos, id) => {
    if (!esArray(productos)) return null;
    
    // find: busca el primer producto que cumpla la condición. 
    // Si no lo encuentra, él solo devuelve undefined (como pide el examen)[cite: 3]
    return productos.find(p => p.id === id && p.stock > 0);
};

// c)
const existeProductoCaroDisponible = (productos, precio) => {
    if (!esArray(productos)) return null;

    // some: devuelve true si AL MENOS UN producto cumple la condición, sino false[cite: 3]
    return productos.some(p => p.stock > 0 && p.precio > precio);
};

// d)
const calcularValorStockDisponible = (productos) => {
    if (!esArray(productos)) return null;

    let valorTotal = 0; // Acumulador al estilo de tu Ejercicio 2

    for (const p of productos) {
        if (p.stock > 0) {
            valorTotal += (p.precio * p.stock); // Sumamos precio por cantidad[cite: 3]
        }
    }
    return valorTotal;
};

// e)
const obtenerProductosRebajados = (productos, porcentaje) => {
    if (!esArray(productos)) return null;

    return productos
        .filter(p => p.stock > 0) // Primero descartamos los que no tienen stock[cite: 3]
        .map(p => {
            // map crea un objeto NUEVO por cada producto para no modificar el original (Inmutabilidad)[cite: 1, 3]
            return {
                ...p, // El operador spread (...) copia todos los datos originales (id, nombre, stock)[cite: 3]
                precio: p.precio - (p.precio * (porcentaje / 100)) // Sobrescribimos el precio con la rebaja[cite: 3]
            };
        });
};


// ==========================================
// EJERCICIO 4: Gestión de jugadores[cite: 4]
// ==========================================

// a)
const actualizarJugador = (jugador, nivel, puntos) => {
    // Robustez: verificamos que nos pasen un objeto[cite: 5]
    if (typeof jugador !== 'object' || jugador === null) return null;

    // Devolvemos un objeto NUEVO sin tocar el original[cite: 4]
    return {
        ...jugador,   // Copiamos los datos viejos (id, nombre)[cite: 4]
        nivel: nivel, // Actualizamos el nivel[cite: 4]
        puntos: puntos, // Actualizamos los puntos[cite: 4]
        activo: true  // Añadimos la nueva propiedad[cite: 4]
    };
};

// b)
const obtenerResumenJugador = (jugador) => {
    if (typeof jugador !== 'object' || jugador === null) return null;

    // Creamos un objeto solo con las dos propiedades que piden[cite: 4]
    return {
        nombre: jugador.nombre,
        puntos: jugador.puntos
    };
};


// ==========================================
// EJERCICIO 5: Gestión de pedidos[cite: 5]
// ==========================================

// a)
const obtenerClientesPagados = (pedidos) => {
    if (!esArray(pedidos)) return null;

    return pedidos
        .filter(p => p.pagado === true) // Filtramos los pagados[cite: 5]
        .map(p => p.cliente); // Extraemos solo el nombre del cliente[cite: 5]
};

// b)
const calcularIngresos = (pedidos) => {
    if (!esArray(pedidos)) return null;

    let ingresosTotales = 0; // Usamos un acumulador como te gusta

    for (const p of pedidos) {
        if (p.pagado === true) {
            ingresosTotales += p.total; // Sumamos el total del pedido[cite: 5]
        }
    }
    return ingresosTotales;
};

// c)
const obtenerPedidoMayor = (pedidos) => {
    if (!esArray(pedidos) || pedidos.length === 0) return null;

    let pedidoMayor = null; // Guardará el pedido más grande que encontremos

    for (const p of pedidos) {
        if (p.pagado === true) {
            // Si es el primer pagado que vemos, o su total es mayor que el que teníamos guardado[cite: 5]
            if (pedidoMayor === null || p.total > pedidoMayor.total) {
                pedidoMayor = p; // Lo guardamos como el nuevo mayor[cite: 5]
            }
        }
    }
    
    return pedidoMayor;
};

// d)
const generarResumen = (pedidos) => {
    if (!esArray(pedidos)) return null;

    // Reutilizamos las funciones que acabamos de crear, llamándolas directamente[cite: 5]
    return {
        clientes: obtenerClientesPagados(pedidos),
        totalIngresado: calcularIngresos(pedidos),
        pedidoMayor: obtenerPedidoMayor(pedidos)
    };
};