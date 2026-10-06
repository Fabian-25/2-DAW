const tareas = [
    { id: 1, titulo: "Estudiar JavaScript", completada: true, prioridad: 2 },
    { id: 2, titulo: "Practicar arrays", completada: false, prioridad: 1 },
    { id: 3, titulo: "Repasar objetos", completada: false, prioridad: 3 }
];


// a) Obtener tareas pendientes
function obtenerPendientes(tareas) {
    return tareas.filter(tarea => !tarea.completada);
}


// b) Buscar una tarea por su ID
function buscarTarea(tareas, id) {
    return tareas.find(tarea => tarea.id === id);
}


// c) Completar una tarea


function completarTarea(tareas, id) {
    return tareas.map(tarea =>
        tarea.id === id
            ? { ...tarea, completada: true }
            : tarea
    );
}


// d) Añadir una tarea
function añadirTarea(tareas, nuevaTarea) {
    return [...tareas, { ...nuevaTarea }];
}


// e) Calcular estadísticas
function calcularEstadisticas(tareas) {
    const total = tareas.length;

    const completadas = tareas.filter(tarea => tarea.completada).length;

    const pendientes = tareas.filter(tarea => !tarea.completada).length;

    return {
        total,
        completadas,
        pendientes
    };
}


// PRUEBAS

console.log("Pendientes:");
console.log(obtenerPendientes(tareas));

console.log("Buscar tarea:");
console.log(buscarTarea(tareas, 2));

console.log("Completar tarea 2:");
const nuevasTareas = completarTarea(tareas, 2);
console.log(nuevasTareas);

console.log("Array original:");
console.log(tareas);

console.log("Añadir tarea:");
const nueva = {
    id: 4,
    titulo: "Practicar reduce",
    completada: false,
    prioridad: 2
};

const tareasConNueva = añadirTarea(tareas, nueva);
console.log(tareasConNueva);

console.log("Estadísticas:");
console.log(calcularEstadisticas(tareas));