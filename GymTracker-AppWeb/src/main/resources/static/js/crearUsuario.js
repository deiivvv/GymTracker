function siguiente(numPagina) {
    let contenedorActual = document.getElementById('idContenedor' + numPagina);
    let contenedorSiguiente = document.getElementById('idContenedor' + (numPagina + 1));

    contenedorActual.classList.toggle('oculto');
    contenedorSiguiente.classList.toggle('oculto');

    contenedorSiguiente.classList.remove('giroVolver');
    setTimeout(function () {
        contenedorSiguiente.classList.add('giroSiguiente');
    }, 10);
}

function volver(numPagina) {
    let contenedorActual = document.getElementById('idContenedor' + numPagina);
    let contenedorAnterior = document.getElementById('idContenedor' + (numPagina - 1));

    contenedorActual.classList.toggle('oculto');
    contenedorAnterior.classList.toggle('oculto');

    contenedorAnterior.classList.remove('giroSiguiente');

    setTimeout(function () {
        contenedorAnterior.classList.add('giroVolver');
    }, 10);
}

function comprobarUsuarioAceptar() {

    document.getElementById("idAlertCrearUsuario").style = "display: none";

    let nombre = document.getElementById("idNombre").value;
    let contrasena = document.getElementById("idContrasena").value;

    if (nombre.trim() === "" || contrasena.trim() === "") {
        document.getElementById("idAlertCrearUsuario").style = "display: block";
        document.getElementById("idAlertCrearUsuario").innerHTML = "Debes rellenar todos los campos";
    } else {
        axios.get('/usuario/comprobar', {
            params: {
                nombre: nombre
            }
        })
            .then(function (response) {
                let usuarioExiste = response.data;
                if (usuarioExiste) {
                    document.getElementById("idAlertCrearUsuario").style = "display: block";
                    document.getElementById("idAlertCrearUsuario").innerHTML = "Nombre de usuario ya en uso";
                } else {
                    siguiente(1)
                }
            })
            .catch(function (error) {
                console.error("Error al hacer la solicitud:", error);
            });
    }
}

function validarEdadAndGenero(accion){
    document.getElementById("idAlertPerfil1").classList.add("alert-primary");
    document.getElementById("idAlertPerfil1").classList.remove("alert-warning");
    document.getElementById("idAlertPerfil1").innerHTML = " No olvides que no es necesario que completes tu perfil en este momento, pero recuerda actualizarlo\n" +
        "                    más tarde.";
    let edad = document.getElementById("idEdad").value;
    let genero = document.getElementById("idGenero").value;

    let opcionesGenero = ["Sin especificar", "Masculino", "Femenino"]
    if(edad < 0 || edad>120 || !opcionesGenero.includes(genero)){
        document.getElementById("idAlertPerfil1").innerHTML = "Campos inválidos";
        document.getElementById("idAlertPerfil1").classList.remove("alert-primary");
        document.getElementById("idAlertPerfil1").classList.add("alert-warning");
        return false;
    }else{
        if(accion==="siguiente"){
            siguiente(2);
        }
        return true;
    }
}

function validarPesoAndAltura(){
    document.getElementById("idAlertPerfil2").classList.add("alert-primary");
    document.getElementById("idAlertPerfil2").classList.remove("alert-warning");
    document.getElementById("idAlertPerfil2").innerHTML = " No olvides que no es necesario que completes tu perfil en este momento, pero recuerda actualizarlo\n" +
        "                    más tarde.";

    let peso = document.getElementById("idPeso").value;
    let altura = document.getElementById("idAltura").value;

    if(peso < 0 || peso>200 || altura < 0 || altura > 300 ){
        document.getElementById("idAlertPerfil2").style = "display: block";
        document.getElementById("idAlertPerfil2").innerHTML = "Campos inválidos";
        document.getElementById("idAlertPerfil2").classList.remove("alert-primary");
        document.getElementById("idAlertPerfil2").classList.add("alert-warning");
        return false;
    }else{
        return true;
    }
}
function validar(event, accion) {
    event.preventDefault();

    if(validarEdadAndGenero(accion) && validarPesoAndAltura()){
        comprobarNombre(event);
    }
}