//Mini Reto: El producto
const producto = {
    nombre: "Monitor",
    precio: 200,
    stock: 3,
    descuento: null,
};

const producto2 = {
    nombre: "Movil",
    precio: 500,
    stock: 0,
    descuento: 50,
};

const obtenerEstado = (producto) => {
       return  producto.stock ? "Disponible" : "Agotado";
};


const obtenerPrecioFinal = (producto) =>{
    if (producto.descuento == null) {
        return producto.precio
    }else{
        const precioFinal = (producto.precio - (producto.precio - (producto.descuento/100)));
        return precioFinal;
    }
}

console.log(obtenerEstado(producto));

console.log(obtenerPrecioFinal(producto2));

