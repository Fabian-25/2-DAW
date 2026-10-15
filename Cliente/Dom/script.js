const nombre = document.querySelector("#texto");
const saludar = document.querySelector("#saludo");
nombre.addEventListener("input", e => saludar.textContent = `Hola, ${e.target.value}`);

const limpiar = document.querySelector("#limpiar");
limpiar.addEventListener("click", ()=> {
    saludar.textContent = "";
    nombre.value = "";
})