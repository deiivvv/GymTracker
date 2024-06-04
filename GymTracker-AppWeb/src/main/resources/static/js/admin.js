function cambiarRol(rol) {
	document.getElementById("idRolHidden").value = rol;
	document.getElementById("idForm").submit();
}

function cambiarContrasena(contrasena) {
	document.getElementById("idContrasenaHidden").value = contrasena;
	document.getElementById("idForm").submit();
}

function marcarUsuario(boton, accion) {
	let row = boton.parentNode.parentNode;
	let tdId = row.querySelector('td:first-child');
	let id = tdId.innerText;
	document.getElementById("idUsuarioHidden").value = id;
	document.getElementById("idAccionHidden").value = accion;

	if (accion === "acceso") {
		let tdRol = row.querySelector('td:nth-child(3)');
		let rol = tdRol.innerText;
		document.getElementById("idRolHidden").value = rol;
		document.getElementById("idForm").submit();
	}

}

function eliminar() {
	document.getElementById("idForm").submit();
}

function comprobarNombre(event) {
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
		.then(function(response) {
			let usuarioExiste = response.data;
			if (usuarioExiste) {
				document.getElementById("idAlertCrearUsuario").classList.remove("alert-primary");
				document.getElementById("idAlertCrearUsuario").classList.add("alert-warning");
				document.getElementById("idAlertCrearUsuario").innerHTML = "Nombre de usuario ya en uso"
				document.getElementById("idAlertCrearUsuario").style = "display:block"
			} else {
				document.getElementById("idAlertCrearUsuario").classList.add("alert-primary");
				document.getElementById("idAlertCrearUsuario").classList.remove("alert-warning");
				document.getElementById("idAlertCrearUsuario").innerHTML = "No olvides que no es necesario que completes tu perfil en este momento, pero recuerda actualizarlo más tarde. Pulsa enter para omitirlo.";

				document.getElementById("idFormCrearUsuario").submit();
			}
		})
		.catch(function(error) {
			console.error("Error al hacer la solicitud:", error);
		});
}

function selectOption(rol) {
	document.getElementById("idSRolHidden").value=rol.value;
	document.getElementById("idPagHidden").value=1;
	rol.classList = 'btn';
	switch (rol.value) {
		case "%":
			rol.classList.add('border-success');
			rol.classList.add('text-secondary');
			break;
		case "administrador":
			rol.classList.add('border-primary');
			rol.classList.add('text-primary');
			break;
		case "usuario":
			rol.classList.add('border-success');
			rol.classList.add('text-success');
			break;
		case "bloqueado":
			rol.classList.add('border-danger');
			rol.classList.add('text-danger');
			break;
	}
	let borrar = document.querySelectorAll("tbody tr")
	borrar.forEach((b) => {
		b.remove();
	})
	axios.get('/admin/usuarios', {
		params: {
			rol: rol.value
		}
	})
		.then(function(response) {
			let tbody = document.getElementById("idBodyUsuarios")
			if (response.data !== null) {
				for (let usuario of response.data) {
					let tr = document.createElement("tr")
					let tdId = document.createElement("td");
					tdId.innerHTML = usuario.id
					let tdNombre = document.createElement("td");
					tdNombre.innerHTML = usuario.nombre
					let tdRol = document.createElement("td");
					tdRol.innerHTML = usuario.rol
					switch (usuario.rol) {
						case "administrador":
							tdRol.innerHTML = '<span class="btn btn-outline-primary"' +
								'onclick="marcarUsuario(this, \'cambiarRol\')" data-bs-toggle="modal" ' +
								'data-bs-target="#idModalCambiarRol">administrador</span>'
							break;
						case "usuario":
							tdRol.innerHTML = '<span class="btn btn-outline-success"' +
								'onclick="marcarUsuario(this, \'cambiarRol\')" data-bs-toggle="modal" ' +
								'data-bs-target="#idModalCambiarRol">usuario</span>'
							break;
						case "bloqueado":
							tdRol.innerHTML = '<span class="btn btn-outline-danger"' +
								'onclick="marcarUsuario(this, \'cambiarRol\')" data-bs-toggle="modal" ' +
								'data-bs-target="#idModalCambiarRol">bloqueado</span>'
							break;
					}
					let tdAcciones = document.createElement("td");
					tdAcciones.innerHTML = '<td>\n' +
						'                    <button onclick="marcarUsuario(this, \'cambiarContrasena\')" type="button"\n' +
						'                            class="btn btn-primary" data-bs-toggle="modal"\n' +
						'                            data-bs-target="#idModalNewContrasena">\n' +
						'                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"\n' +
						'                             fill="currentColor" class="bi bi-key-fill" viewBox="0 0 16 16">\n' +
						'                            <path d="M3.5 11.5a3.5 3.5 0 1 1 3.163-5H14L15.5 8 14 9.5l-1-1-1 1-1-1-1 1-1-1-1 1H6.663a3.5 3.5 0 0 1-3.163 2M2.5 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>\n' +
						'                        </svg>\n' +
						'                    </button>\n' +
						'                    <button onclick="marcarUsuario(this, \'acceso\')" type="button"\n' +
						'                            class="btn btn-success ">\n' +
						'                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"\n' +
						'                             fill="currentColor" class="bi bi-door-open-fill"\n' +
						'                             viewBox="0 0 16 16">\n' +
						'                            <path\n' +
						'                                    d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15zM11 2h.5a.5.5 0 0 1 .5.5V15h-1zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1"/>\n' +
						'                        </svg>\n' +
						'                    </button>\n' +
						'                    <button onclick="marcarUsuario(this, \'eliminar\')" type="button"\n' +
						'                            class="btn btn-danger mr-1" data-bs-toggle="modal"\n' +
						'                            data-bs-target="#idModalEliminar">\n' +
						'                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"\n' +
						'                             fill="currentColor" class="bi bi-trash-fill"\n' +
						'                             viewBox="0 0 16 16">\n' +
						'                            <path\n' +
						'                                    d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>\n' +
						'                        </svg>\n' +
						'                    </button>\n' +
						'                </td>';
					let tdUsuario = document.createElement("td");
					let idUsuario = document.getElementById("idUsuario").value;
					if (usuario.id === parseInt(document.getElementById("idUsuario").value)) {
						tdUsuario.innerHTML = '<div class="btn btn-secondary">\n' +
							'                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"\n' +
							'                             fill="currentColor" class="bi bi-person-fill"\n' +
							'                             viewBox="0 0 16 16">\n' +
							'                            <path\n' +
							'                                    d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>\n' +
							'                        </svg>\n' +
							'                    </div>'
					}
					tbody.appendChild(tr);
					tr.appendChild(tdId);
					tr.appendChild(tdNombre);
					tr.appendChild(tdRol);
					tr.appendChild(tdAcciones);
					tr.appendChild(tdUsuario);
				}
				usuariosIniciales();
			} else {
				tr.innerHTML = "No se encuentran usuarios " + usuario.rol;
			}
		})
		.catch(function(error) {
			console.error("Error al hacer la solicitud:", error);
		});
}

let pagInicial = 1;
let pagFinal = 1;
let pagActual = 1;
let usuarios = []
let usu = []
function usuariosIniciales() {
	usu = [];
	usuarios = document.querySelectorAll('#idBodyUsuarios tr');
	pagFinal = Math.ceil(usuarios.length / 5);
	document.getElementById("idNumUltimaPag").value = pagFinal;
	let temp = [];
	usuarios.forEach((usuario, index) => {
		temp.push(usuario);
		if ((index + 1) % 5 === 0 || index === usuarios.length - 1) {
			usu.push(temp);
			temp = [];
		}
	});
	let num=document.getElementById("idPagHidden").value
	if(num>pagFinal) num-=1;
	cambiarPagina(num);
}

function paginacion() {
	let boolean = true;
	let li = document.querySelectorAll(`#idDivPaginas li`)
	li.forEach((l) => {
		l.remove();
	});

	if (pagInicial !== pagFinal) {
		if (pagInicial === pagActual) {
			boolean = false;
			document.getElementById("idFirstPag").disabled = "true";
			for (let i = pagInicial; i < pagActual + 3 && i <= pagFinal; i++) {
				crearLi(i);
			}
		}
		if (pagFinal === pagActual) {
			boolean = false;
			document.getElementById("idLastPag").disabled = "true";
			let resta = 2;
			if (pagFinal === 2) { resta = 1; }
			for (let i = pagActual - resta; i < pagActual + 1 && i >= pagInicial; i++) {
				crearLi(i);
			}
		}
		if (boolean) {
			for (let i = pagActual - 1; i <= (pagActual + 1); i++) {
				crearLi(i);
			}
		}
	}
}

function crearLi(num) {
	let divPaginas = document.getElementById("idDivPaginas");
	let newLi = document.createElement("li");
	newLi.classList.add("page-item", "d-inline-block");
	let newA = document.createElement("a");
	if (num === pagActual) {
		newA.classList.add("page-link", "text-white", "bg-success");
	} else {
		newA.classList.add("page-link", "text-success");
	}
	newA.addEventListener("click", () => {
		cambiarPagina(newA.innerHTML);
	});
	newA.innerHTML = num
	divPaginas.appendChild(newLi);
	newLi.appendChild(newA);
}

function usuariosPaginados() {
	let bodyUsuarios = document.getElementById("idBodyUsuarios");
	let limpiarusuarios = document.querySelectorAll("#idBodyUsuarios tr")
	limpiarusuarios.forEach((l) => {
		l.remove();
	});
	usu[pagActual - 1].forEach((u) => {
		bodyUsuarios.appendChild(u);
	});
}

function cambiarPagina(num) {
	document.getElementById("idPagHidden").value=num;
	pagActual = parseInt(num);
	paginacion();
	usuariosPaginados()
	if (pagActual === pagInicial) {
		document.getElementById("idFirstPag").style = "visibility: hidden"
	} else {
		document.getElementById("idFirstPag").style = "visibility: visible"
	}
	if (pagActual === pagFinal) {
		document.getElementById("idLastPag").style = "visibility: hidden"
	} else {
		document.getElementById("idLastPag").style = "visibility: visible"
	}
}