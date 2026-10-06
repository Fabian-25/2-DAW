let productos = [
    { nombre: "Teclado", precio: 35, stock: 4 },
    { nombre: "Ratón", precio: 20, stock: 0 },
    { nombre: "Monitor", precio: 180, stock: 3 },
    { nombre: "Auriculares", precio: 45, stock: 8 }
];

function estaDisponible(productos) {
    if (productos.stock > 0) {
        console.log("True");
    } else {
        console.log("False");
    }
};

for (let i in productos) {
    estaDisponible(productos[i]);

}

//Tarea 2
for (const producto of productos) {
    console.log(`${producto.nombre} - ${producto.precio} - Stock: ${producto.stock}`);
}
//Fin Tarea2


//Tarea 3
function totalProductos() {
    for (const producto of productos) {
        total += producto.stock * producto.precio;
    }
    return total;
}
console.log(totalProductos);
//Fin Tarea 3

//Tarea 4
//Seleccionar: <ul id="lista"></ul> y mostrar unicamente los productos que tengan unidades disponibles.
const lista = document.querySelector("#lista");
for (const producto of productos) {
    if (producto.stock > 0) {
        const item = document.createElement('li');
        item.textContent = `${producto.nombre} - ${producto.precio}€`;
        lista.appendChild(item);
    }
}    
//Fin Tarea 4

//Tarea 5
lista.style.display = 'none';
const mostrar = document.querySelector("#mostrar");
mostrar.addEventListener('click', () => ) //falta algo
//Fin tarea 5

//Tarea 6
const resumen = document.querySelector("#resumen");
let totalProductosStock = 0;
for (const producto of productos) {
    //if (producto.stock ) //falta algo
}
//Fin tarea 6

//Mini Reto: El producto