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