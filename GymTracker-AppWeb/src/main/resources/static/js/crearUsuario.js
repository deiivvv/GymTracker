function siguiente(numPagina) {
    document.getElementById('idContenedor' + numPagina).classList.add('oculto', 'girado');
    let contenedorMas1 =document.getElementById('idContenedor' + (parseInt(numPagina + 1)))
    contenedorMas1.classList.remove('oculto')
    contenedorMas1.classList.add('giro');
}

function volver(numPagina) {
    document.getElementById('idContenedor' + numPagina).classList.add('oculto', 'girado');
    let contenedorMenos1 =document.getElementById('idContenedor' + (parseInt(numPagina - 1)))
    contenedorMenos1.classList.remove('oculto')
    contenedorMenos1.classList.add('giro');
}