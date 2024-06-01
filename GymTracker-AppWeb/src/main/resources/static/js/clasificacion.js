function selectOption(estadistica) {
	document.getElementById("idSelectHidden").value= estadistica.value;
	let borrar = document.querySelectorAll("tbody tr")
	borrar.forEach((b) => {
		b.remove();
	})
	axios.get('/clasificacion/ordenar', {
		params: {
			estadistica: estadistica.value
		}
	})
		.then(function(response) {
			let tbody = document.getElementById("idBodyUsuarios")
			if (response.data !== null) {
				for (let usuario of response.data) {
					let tr = document.createElement("tr")
					let tdNombre = document.createElement("td");
					tdNombre.innerHTML = usuario.nombre
					let tdEntrenos = document.createElement("td");
					tdEntrenos.innerHTML = usuario.entrenos
					let tdEjercicios = document.createElement("td");
					tdEjercicios.innerHTML = usuario.ejercicios
					let tdVolumen = document.createElement("td");
					tdVolumen.innerHTML = usuario.volumen
					let tdSeries = document.createElement("td");
					tdSeries.innerHTML = usuario.series
					let tdUsuario = document.createElement("td");
					console.log(usuario.id);
					let idUsuario = document.getElementById("idUsuario").value;
					console.log(idUsuario);
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
					tr.appendChild(tdNombre);
					tr.appendChild(tdEntrenos);
					tr.appendChild(tdEjercicios);
					tr.appendChild(tdVolumen);
					tr.appendChild(tdSeries);
					tr.appendChild(tdUsuario);
				}
				usuariosIniciales();
			} else {
				tr.innerHTML = "No se encuentran usuarios";
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