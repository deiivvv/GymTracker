function cambiarRol(rol) {
    document.getElementById("idRolHidden").value = rol;
    document.getElementById("idFormRol").submit();
}

function marcarUsuario(boton, accion) {
    let row = boton.parentNode.parentNode;
    let tdId = row.querySelector('td:first-child');
    let id = tdId.innerText;
    document.getElementById("idUsuarioHidden").value = id;
    document.getElementById("idAccionHidden").value = accion;

    if(accion==="acceso"){
		let tdRol = row.querySelector('td:nth-child(3)');
		let rol = tdRol.innerText;
		document.getElementById("idRolHidden").value = rol;
        document.getElementById("idFormRol").submit();
    }

}

function eliminar(){
	 document.getElementById("idFormRol").submit();
}

function comprobarNombre(event){
    event.preventDefault();

    let nombre = document.getElementById("idNombre").value;

	document.getElementById("idAlertCrearUsuario").classList.add("alert-primary");
    document.getElementById("idAlertCrearUsuario").classList.remove("alert-warning");
    document.getElementById("idAlertCrearUsuario").innerHTML = "No olvides que no es necesario que completes tu perfil en este momento, pero recuerda actualizarlo más tarde. Pulsa enter para omitirlo.";

    axios.get('/usuario/comprobar', {
        params: {
            nombre: nombre
        }
    })
        .then(function (response) {
            let usuarioExiste = response.data;
            if (usuarioExiste) {
                document.getElementById("idAlertCrearUsuario").classList.remove("alert-primary");
    			document.getElementById("idAlertCrearUsuario").classList.add("alert-warning");
        		document.getElementById("idAlertCrearUsuario").innerHTML = "Nombre de usuario ya en uso"
                document.getElementById("idAlertCrearUsuario").style="display:block"
            }else{
                document.getElementById("idAlertCrearUsuario").classList.add("alert-primary");
    			document.getElementById("idAlertCrearUsuario").classList.remove("alert-warning");
    			document.getElementById("idAlertCrearUsuario").innerHTML = "No olvides que no es necesario que completes tu perfil en este momento, pero recuerda actualizarlo más tarde. Pulsa enter para omitirlo.";
        		
                document.getElementById("idFormCrearUsuario").submit();
            }
        })
        .catch(function (error) {
            console.error("Error al hacer la solicitud:", error);
        });
}
