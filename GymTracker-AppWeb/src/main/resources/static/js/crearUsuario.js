function siguiente(numPagina) {
    let contenedorActual = document.getElementById('idContenedor' + numPagina);
    let contenedorSiguiente = document.getElementById('idContenedor' + (numPagina + 1));
    
    contenedorActual.classList.toggle('oculto');
    contenedorSiguiente.classList.toggle('oculto');
    
    contenedorSiguiente.classList.remove('giroVolver');
    setTimeout(function() {
    	contenedorSiguiente.classList.add('giroSiguiente');
    }, 10);
}

function volver(numPagina) {
    let contenedorActual = document.getElementById('idContenedor' + numPagina);
    let contenedorAnterior = document.getElementById('idContenedor' + (numPagina - 1));
	
	contenedorActual.classList.toggle('oculto');
	contenedorAnterior.classList.toggle('oculto');
	
	contenedorAnterior.classList.remove('giroSiguiente');
	
	setTimeout(function() {
    	contenedorAnterior.classList.add('giroVolver');
    }, 10);
}

