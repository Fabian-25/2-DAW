
const formulario = document.querySelector('#registro');
const inputNombre = document.querySelector('#nombre');
const inputEmail = document.querySelector('#email');
const parrafoVista = document.querySelector('#vista');
const parrafoMensaje = document.querySelector('#mensaje');
const lista = document.querySelector('#lista');
const contadorTotal = document.querySelector('#total');
const btnVaciar = document.querySelector('#vaciar');
const inputBuscar = document.querySelector('#buscar');
const avisoLista = document.querySelector('#avisoLista');

let filaEditando = null;

// Actualiza el contador y los resultados de la búsqueda.
function actualizarLista() {
    contadorTotal.textContent = lista.children.length;

    const busqueda = inputBuscar.value.trim().toLowerCase();
    let coincidencias = 0;

    for (const fila of lista.children) {
        fila.hidden = !(
            fila.dataset.nombre.toLowerCase().includes(busqueda) ||
            fila.dataset.email.includes(busqueda)
        );

        if (!fila.hidden) {
            coincidencias++;
        }
    }

    if (lista.children.length === 0) {
        avisoLista.textContent = 'No hay participantes registrados.';
    } else if (coincidencias === 0) {
        avisoLista.textContent =
            'No hay participantes que coincidan con la búsqueda.';
    } else {
        avisoLista.textContent = '';
    }
}

// Devuelve el formulario al modo de registro.
function cancelarEdicion() {
    filaEditando = null;
    formulario.reset();
    parrafoVista.textContent = '';
    formulario.querySelector('[type="submit"]').textContent = 'Registrar';
    document.querySelector('#cancelar').hidden = true;
}

// Crea una fila con los datos y sus botones.
function crearParticipante(nombre, email) {
    const fila = document.createElement('li');
    const datos = document.createElement('span');

    fila.dataset.nombre = nombre;
    fila.dataset.email = email.trim().toLowerCase();
    datos.textContent = `${nombre} — ${email}`;

    const editar = document.createElement('button');
    editar.type = 'button';
    editar.textContent = 'Editar';

    editar.addEventListener('click', () => {
        filaEditando = fila;
        inputNombre.value = fila.dataset.nombre;
        inputEmail.value = fila.dataset.email;
        parrafoVista.textContent = `Hola, ${fila.dataset.nombre}`;
        formulario.querySelector('[type="submit"]').textContent = 'Guardar cambios';
        document.querySelector('#cancelar').hidden = false;
        inputNombre.focus();
    });

    const eliminar = document.createElement('button');
    eliminar.type = 'button';
    eliminar.textContent = 'Eliminar';

    eliminar.addEventListener('click', () => {
        if (!confirm('¿Quieres eliminar este participante?')) {
            return;
        }

        if (filaEditando === fila) {
            cancelarEdicion();
        }

        fila.remove();
        actualizarLista();
        parrafoMensaje.textContent = 'Participante eliminado.';
        inputNombre.focus();
    });

    fila.append(datos, editar, eliminar);
    lista.append(fila);
}

// Muestra el saludo mientras se escribe.
inputNombre.addEventListener('input', (event) => {
    const nombre = event.target.value.trim();
    parrafoVista.textContent = nombre ? `Hola, ${nombre}` : '';
});

// Filtra la lista mientras se escribe.
inputBuscar.addEventListener('input', actualizarLista);

// Registra un participante o guarda los cambios de una edición.
formulario.addEventListener('submit', (event) => {
    event.preventDefault();
    parrafoMensaje.textContent = '';

    const nombreLimpio = inputNombre.value.trim();
    const emailLimpio = inputEmail.value.trim();

    inputNombre.value = nombreLimpio;
    inputEmail.value = emailLimpio;

    if (!formulario.reportValidity()) {
        return;
    }

    if (nombreLimpio.length < 2) {
        parrafoMensaje.textContent = 'Nombre: mínimo 2 caracteres.';
        return;
    }

    // Comprueba duplicados, excepto la propia fila al editar.
    for (const fila of lista.children) {
        if (fila !== filaEditando &&
            fila.dataset.email === emailLimpio.toLowerCase()) {
            parrafoMensaje.textContent = 'Este correo ya está registrado.';
            return;
        }
    }

    if (filaEditando) {
        filaEditando.dataset.nombre = nombreLimpio;
        filaEditando.dataset.email = emailLimpio.toLowerCase();
        filaEditando.querySelector('span').textContent =
            `${nombreLimpio} — ${emailLimpio}`;

        cancelarEdicion();
        parrafoMensaje.textContent = 'Participante actualizado.';
    } else {
        crearParticipante(nombreLimpio, emailLimpio);
        formulario.reset();
        parrafoVista.textContent = '';
        parrafoMensaje.textContent = 'Participante registrado.';
    }

    actualizarLista();
    inputNombre.focus();
});

// Crea el botón para cancelar la edición.
const btnCancelar = document.createElement('button');
btnCancelar.type = 'button';
btnCancelar.id = 'cancelar';
btnCancelar.textContent = 'Cancelar edición';
btnCancelar.hidden = true;
formulario.append(btnCancelar);

btnCancelar.addEventListener('click', () => {
    cancelarEdicion();
    parrafoMensaje.textContent = 'Edición cancelada.';
    inputNombre.focus();
});

// Vacía la lista y cancela la edición si es necesario.
btnVaciar.addEventListener('click', () => {
    if (lista.children.length > 0 &&
        !confirm('¿Quieres vaciar toda la lista?')) {
        return;
    }

    lista.replaceChildren();

    if (filaEditando) {
        cancelarEdicion();
    }

    actualizarLista();
    parrafoMensaje.textContent = 'Lista vacía.';
    inputNombre.focus();
});

// Inicializa el contador y el aviso.
actualizarLista();
