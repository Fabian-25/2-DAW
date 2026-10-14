const formulario = document.querySelector('#registro');
const inputNombre = document.querySelector('#nombre');
const inputEmail = document.querySelector('#email');
const parrafoVista = document.querySelector('#vista');
const parrafoMensaje = document.querySelector('#mensaje');
const lista = document.querySelector('#lista');
const contadorTotal = document.querySelector('#total');
const btnVaciar = document.querySelector('#vaciar');

inputNombre.addEventListener('input', (event) => {
    const nombreEscrito = event.target.value.trim();
    
    if (nombreEscrito === "") {
        parrafoVista.textContent = ""; 
    } else {
        parrafoVista.textContent = `Hola, ${nombreEscrito}`;
    }
});

formulario.addEventListener('submit', (event) => {
    event.preventDefault();
    
    parrafoMensaje.textContent = "";
    
    const nombreLimpio = inputNombre.value.trim();
    const emailLimpio = inputEmail.value.trim();
    inputNombre.value = nombreLimpio;
    inputEmail.value = emailLimpio;
    
    if (!formulario.reportValidity()) {
        return; 
    }
    
    if (nombreLimpio.length < 2) {
        parrafoMensaje.textContent = "Nombre: mínimo 2 caracteres.";
        return; 
    }

    const nuevoParticipante = document.createElement('li');
    nuevoParticipante.textContent = `${nombreLimpio} — ${emailLimpio}`;
    lista.append(nuevoParticipante); 
    
    contadorTotal.textContent = lista.children.length;
    
    formulario.reset();
    parrafoVista.textContent = "";
    parrafoMensaje.textContent = "Participante registrado.";
    inputNombre.focus();
});

btnVaciar.addEventListener('click', () => {
    lista.replaceChildren(); 
    contadorTotal.textContent = "0";
    parrafoMensaje.textContent = "Lista vacía.";
});