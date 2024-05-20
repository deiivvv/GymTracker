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

function comprobarContrasena(){
    let contrasena = document.getElementById("idContrasena").value;
    axios.get('/perfil/cambiar-contrasena', {
        params: {
            contrasena: contrasena
        }
    })
        .then(function (response) {
            let contrasenaCorrecta = response.data;
            if (contrasenaCorrecta) {
                $('#idModalContrasena').modal('hide');
                $('#idModalNewContrasena').modal('show');
            } else {
                document.getElementById("idAlertContrasena").style = "display:block";
            }
        })
        .catch(function (error) {
            console.error("Error al hacer la solicitud:", error);
        });
}

function compararNewContrasena(){
    document.getElementById("idBotonAceptarContrasena").disabled=!(document.getElementById("idNewContrasena").value===document.getElementById("idNewContrasena2").value);
}