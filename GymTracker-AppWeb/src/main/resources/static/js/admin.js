function cambiarRol(rol) {
    document.getElementById("idRolHidden").value = rol;
    document.getElementById("idFormRol").submit();
}

function marcarUsuario(boton, accion) {
    let row = boton.parentNode.parentNode;
    let td = row.querySelector('td:first-child');
    let id = td.innerText;
    document.getElementById("idUsuarioHidden").value = id;
    document.getElementById("idAccionHidden").value = accion;

    if(accion==="acceso"){
        document.getElementById("idFormRol").submit();
    }

}
