function siguiente(numPagina) {
    let contenedorActual = document.getElementById('idContenedor' + numPagina);
    contenedorActual.classList.toggle('oculto');
    let contenedorSiguiente = document.getElementById('idContenedor' + (numPagina + 1));
    contenedorSiguiente.classList.toggle('oculto');
}

function volver(numPagina) {
    let contenedorActual = document.getElementById('idContenedor' + numPagina);
    let contenedorAnterior = document.getElementById('idContenedor' + (numPagina - 1));

    contenedorActual.classList.toggle('oculto');
    contenedorAnterior.classList.toggle('oculto');
}