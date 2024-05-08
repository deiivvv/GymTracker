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

function comprobarUsuarioAceptar(){

    let nombre = document.getElementById("idNombre").value;

    axios.get('/usuario/comprobar', {
        params: {
            nombre: nombre
        }
    })
        .then(function (response) {
            let usuarioExiste = response.data;
            if (usuarioExiste) {
                document.getElementById("idAlertNombreUsuario").style="display: block";
            }else{
                document.getElementById("idAlertNombreUsuario").style="display: none";
                siguiente(1)
            }
        })
        .catch(function (error) {
            console.error("Error al hacer la solicitud:", error);
        });
}