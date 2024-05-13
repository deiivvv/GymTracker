function validarModificacion(event) {
    event.preventDefault();
    let alerts = ["idAlertCrearUsuario", "idAlertPerfil1", "idAlertPerfil2"]
    alerts.forEach((a) => {
        document.getElementById(a).style = "display: none";
    });

    if (validarEdadAndGenero("modificar")) {
        if (validarPesoAndAltura()) {
            document.getElementById("idAlertPerfil1").style="display:none"
            if (document.getElementById("idNombre").value !== document.getElementById("idNombreAntiguo").value) {
                document.getElementById("idAlertPerfil2").style="display:none"
                comprobarNombre(event);
            }else{document.getElementById("idFormCrearUsuario").submit();}
        }else{document.getElementById("idAlertPerfil2").style="display:block"}
    }else{document.getElementById("idAlertPerfil1").style="display:block"}
}